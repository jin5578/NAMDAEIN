package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context


class MarketDetailPresenter: MarketDetailContract.Presenter {

    private lateinit var view: MarketDetailContract.View
    private lateinit var context: Context


    override fun setView(view: MarketDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}