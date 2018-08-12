package com.tistory.jeongs0222.namdaein.ui.activity.notice

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.tistory.jeongs0222.namdaein.api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NoticePresenter: NoticeContract.Presenter {

    private lateinit var view: NoticeContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

    private lateinit var mAdapter: NoticeItemAdapter

    private val apiClient by lazy { ApiClient.create() }

    override fun setView(view: NoticeContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = NoticeItemAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter

            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData() {
        compositeDisposable
                .add(apiClient.noticeInformation()
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            if(it.notice.isNotEmpty()) {
                                mAdapter.addAllItems(it.notice)
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe()
                )
    }

    override fun disposableClear() = compositeDisposable.clear()
}