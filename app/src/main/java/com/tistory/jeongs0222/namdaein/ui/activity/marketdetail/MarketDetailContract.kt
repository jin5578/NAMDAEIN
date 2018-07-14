package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.tistory.jeongs0222.namdaein.model.Model


interface MarketDetailContract {

    interface View {
        fun progressBar(value: Int)

        fun imageViewPagerVisible(value: Int)

        fun recyclerView(): RecyclerView
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (String, Model.marketItem) -> Unit)

        fun setUpRecyclerView()

        fun setUpCommentData()
    }
}