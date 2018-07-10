package com.tistory.jeongs0222.namdaein.ui.fragment.market.appliance

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.ui.fragment.market.MarketItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MarketAppliancePresenter: MarketApplianceContract.Presenter {

    private lateinit var view: MarketApplianceContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var pageNumber: Int = 0

    private var isFirstLoad = true

    private var isLoading = false

    private lateinit var mAdapter: MarketItemAdapter

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: MarketApplianceContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = MarketItemAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData() {
        view.progressBar(0)
        isLoading = true

        disposable = apiClient.bringMarket(6, pageNumber)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    if(it.market.isNotEmpty()) {
                        mAdapter.addAllItems(it.market)
                        pageNumber += it.market.size
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    if (isFirstLoad) {
                        isFirstLoad = false
                    }
                    mAdapter.notifyChanged()
                    isLoading = false
                }
                .doOnError {
                    it.printStackTrace()
                    isLoading = false
                }
                .subscribe {
                    view.progressBar(1)

                    if(mAdapter.itemCount == 0) {
                        view.emptyTextVisible()
                    }
                }
    }

    override fun loadMore() {
        val linearLayoutManager: LinearLayoutManager = view.recyclerView().layoutManager as LinearLayoutManager

        view.recyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!isLoading && linearLayoutManager.itemCount - 1 == linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                    setUpData()
                }
            }
        })    }

    override fun disposableClear() = disposable!!.dispose()
}