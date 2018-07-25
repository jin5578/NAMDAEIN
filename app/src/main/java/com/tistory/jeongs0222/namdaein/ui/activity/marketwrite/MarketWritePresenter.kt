package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.content.Context
import android.widget.ArrayAdapter
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class MarketWritePresenter: MarketWriteContract.Presenter {

    private lateinit var view: MarketWriteContract.View
    private lateinit var context: Context

    private val spinnerList = arrayOf("여성의류", "남성의류", "패션잡화", "뷰티", "도서", "티켓", "가전제품", "생활", "원룸", "기타")

    private var compositeDisposable = CompositeDisposable()

    private var currentDate: String = ""

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
        compositeDisposable
                .add(apiClient.beforeMarketData(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError {
                            it.printStackTrace()
                        }
                        .subscribe {
                            callback("complete", it)
                            view.spinner().setText(spinnerList.get(it.category))
                        }
                )
    }

    override fun setUpEditConfirmFunc(order: Int) {
        view.progressBar(0)

        bringDate()

        if(view.title().text.isNotEmpty() && view.content().text.isNotEmpty() && view.price().text.isNotEmpty()) {
            compositeDisposable
                    .add(apiClient.afterMarketData(order, view.title().text.toString(), view.content().text.toString(), view.price().text.toString(), currentDate)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                view.viewFinish()
                            }
                            .doOnError {
                                it.printStackTrace()

                                view.progressBar(1)
                            }
                            .subscribe()
                    )
        } else {
            view.toastMessage("빈 칸은 작성할 수 없습니다.")
        }

    }

    override fun setUpConfirmFunc() {

    }

    private fun bringDate() {
        val now = System.currentTimeMillis()

        val date = Date(now)

        val sdf = SimpleDateFormat("yy.MM.dd HH:mm")

        currentDate = sdf.format(date)
    }

    override fun disposableClear() = compositeDisposable.clear()
}