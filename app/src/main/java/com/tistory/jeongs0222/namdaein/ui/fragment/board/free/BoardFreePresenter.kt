package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardFreePresenter: BoardFreeContract.Presenter {

    private lateinit var view: BoardFreeContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private lateinit var item: MutableList<Model.boardItem>

    private lateinit var mAdapter: BoardItemAdapter

    private val apiClient by lazy {
        ApiClient.create()
    }


    override fun setView(view: BoardFreeContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        view.recyclerView().apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData() {

        disposable = apiClient.bringBoard(0, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    item = it.board
                }
                .doOnComplete {
                    mAdapter = BoardItemAdapter(item, context)
                    view.recyclerView().adapter = mAdapter
                }
                .subscribe(
                        { result -> Log.v("ARTICLES",""+result)},
                        { error -> Log.e("ERROR", error.message) }
                )
    }

    /*private fun setUpRecyclerView() {
        mAdapter = BoardItemAdapter(item, context)
        view.recyclerView().apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)

            adapter = mAdapter
        }
    }*/
}
