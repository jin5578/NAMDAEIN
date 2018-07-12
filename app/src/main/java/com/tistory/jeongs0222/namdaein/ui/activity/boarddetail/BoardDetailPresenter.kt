package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.CommentAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardDetailPresenter: BoardDetailContract.Presenter {

    private lateinit var view: BoardDetailContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var order: Int = 0

    private lateinit var mAdapter: CommentAdapter

    private val apiClient by lazy {
        ApiClient.create()
    }

    override fun setView(view: BoardDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (String, Model.boardItem) -> Unit) {
        this.order = order

        view.progressBar(0)

        disposable = apiClient.bringBoardDetail(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {

                }
                .doOnError {
                    it.printStackTrace()

                    view.progressBar(1)
                }
                .subscribe({
                    if(it.image0.isEmpty()) {
                        view.imageViewPagerVisible(1)
                    } else {
                        view.imageViewPagerVisible(0)
                    }

                    callback("complete", it)
                })
    }

    override fun setUpRecyclerView() {
        mAdapter = CommentAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpCommentData() {
        disposable = apiClient.bringBoardComment(order)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    if(it.comment.isNotEmpty()) {
                        mAdapter.addAllItems(it.comment)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    mAdapter.notifyChanged()
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()
    }

    override fun disposableClear() = disposable!!.dispose()
}