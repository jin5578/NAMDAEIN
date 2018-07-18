package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.content.Context
import android.widget.ArrayAdapter


class MarketWritePresenter: MarketWriteContract.Presenter {

    private lateinit var view: MarketWriteContract.View
    private lateinit var context: Context

    private val spinnerList = arrayOf("여성의류", "남성의류", "패션잡화", "뷰티", "도서", "티켓", "가전제품", "생활", "원룸", "기타")

    override fun setView(view: MarketWriteContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpSpinnerFunc() {
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, spinnerList)

        view.spinner().setAdapter(arrayAdapter)

    }
}