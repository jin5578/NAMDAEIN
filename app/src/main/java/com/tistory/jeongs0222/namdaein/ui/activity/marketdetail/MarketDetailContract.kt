package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context


interface MarketDetailContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}