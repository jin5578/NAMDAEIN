package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.content.Context
import android.widget.ArrayAdapter
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MarketWritePresenter: MarketWriteContract.Presenter {

    private lateinit var view: MarketWriteContract.View
    private lateinit var context: Context

    private val spinnerList = arrayOf("여성의류", "남성의류", "패션잡화", "뷰티", "도서", "티켓", "가전제품", "생활", "원룸", "기타")

    private var disposable: Disposable? = null

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: MarketWriteContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpSpinnerFunc() {
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, spinnerList)

        view.spinner().setAdapter(arrayAdapter)
    }

    override fun setUpBringMarket(order: Int, callback: (String, Model.marketItem) -> Unit) {
        disposable = apiClient.beforMarketData(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {

                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe {
                    callback("complete", it)
                    view.spinner().setText(spinnerList.get(it.category))
                }
    }
}