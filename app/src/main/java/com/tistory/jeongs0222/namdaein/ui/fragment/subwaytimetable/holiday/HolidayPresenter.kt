package com.tistory.jeongs0222.namdaein.ui.fragment.subwaytimetable.holiday

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.ui.fragment.subwaytimetable.SubwayItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HolidayPresenter: HolidayContract.Presenter {

    private lateinit var view: HolidayContract.View
    private lateinit var context: Context

    private lateinit var mAdapter: SubwayItemAdapter

    private var compositeDisposable = CompositeDisposable()

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: HolidayContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = SubwayItemAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter

            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }

    }

    override fun setUpData() {
        compositeDisposable
                .add(apiClient.subwaytimetable(1)
                        .subscribeOn(Schedulers.io())
                        .doOnNext { mAdapter.addAllItems(it.subwaytimetable) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete { mAdapter.notifyChanged() }
                        .doOnError { it.printStackTrace() }
                        .subscribe()
                )
    }

    override fun disposableClear() = compositeDisposable.clear()
}