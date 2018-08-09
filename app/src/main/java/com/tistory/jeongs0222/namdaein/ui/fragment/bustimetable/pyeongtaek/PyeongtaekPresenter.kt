package com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.pyeongtaek

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.tistory.jeongs0222.namdaein.api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PyeongtaekPresenter: PyeongtaekContract.Presenter {

    private lateinit var view: PyeongtaekContract.View
    private lateinit var context: Context

    private lateinit var mAdapter: PyeongtaekItemAdapter

    private var compositeDisposable = CompositeDisposable()

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: PyeongtaekContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = PyeongtaekItemAdapter(context)

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
                .add(apiClient.bustimetable(3)
                        .subscribeOn(Schedulers.io())
                        .doOnNext { mAdapter.addAllItems(it.bustimetable) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete { mAdapter.notifyChanged() }
                        .doOnError { it.printStackTrace() }
                        .subscribe()
                )

    }

    override fun disposableClear() = compositeDisposable.clear()
}