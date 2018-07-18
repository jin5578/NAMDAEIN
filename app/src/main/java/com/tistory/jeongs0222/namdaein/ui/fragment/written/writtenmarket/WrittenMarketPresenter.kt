package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class WrittenMarketPresenter : WrittenMarketContract.Presenter {

    private lateinit var view: WrittenMarketContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private lateinit var mAdapter: WrittenMarketAdapter

    private lateinit var dbHelper: DBHelper

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: WrittenMarketContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpRecyclerView() {
        mAdapter = WrittenMarketAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData() {
        disposable = apiClient.bringWrittenMarket(dbHelper.getGoogle_uId()!!)
                .subscribeOn(Schedulers.io())
                .doOnNext { if (it.writtenMarket.isNotEmpty()) {
                    mAdapter.clearAllItems()
                    mAdapter.addAllItems(it.writtenMarket)
                } }
                .doOnError { it.printStackTrace() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    if (mAdapter.itemCount == 0) view.emptyTextVisible()
                    else mAdapter.notifyChanged()
                }
                .subscribe()
    }

    override fun disposableClear() = disposable!!.dispose()
}