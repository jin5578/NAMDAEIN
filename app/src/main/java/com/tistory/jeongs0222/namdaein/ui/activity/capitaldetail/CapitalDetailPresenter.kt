package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.content.Context
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CapitalDetailPresenter: CapitalDetailContract.Presenter {

    private lateinit var view: CapitalDetailContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: CapitalDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (Model.busTimeTable) -> Unit) {
        compositeDisposable
                .add(apiClient.capitaldetail(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe {
                            callback(it)
                        })
    }

}