package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import com.tistory.jeongs0222.namdaein.model.Model


interface MarketDetailContract {

    interface View {
        fun progressBar(value: Int)

        fun imageViewPagerVisible(value: Int)

    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (String, Model.marketItem) -> Unit)
    }
}