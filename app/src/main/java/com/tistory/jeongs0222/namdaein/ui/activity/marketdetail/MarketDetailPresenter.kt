package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MarketDetailPresenter: MarketDetailContract.Presenter {

    private lateinit var view: MarketDetailContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var order: Int = 0

    private val apiClient by lazy { ApiClient.create() }

    override fun setView(view: MarketDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (String, Model.marketItem) -> Unit) {
        this.order = order

        view.progressBar(0)

        disposable = apiClient.bringMarketDetail(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {  }
                .doOnError {
                    it.printStackTrace()
                    view.progressBar(1)
                }
                .subscribe ({
                    if(it.image0.isEmpty()) {
                        view.imageViewPagerVisible(1)
                    } else {
                        view.imageViewPagerVisible(0)
                    }

                    callback("complete", it)
                })

    }
}