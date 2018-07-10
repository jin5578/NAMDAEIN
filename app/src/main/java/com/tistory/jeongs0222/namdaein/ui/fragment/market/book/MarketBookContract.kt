package com.tistory.jeongs0222.namdaein.ui.fragment.market.book

import android.content.Context
import android.support.v7.widget.RecyclerView


interface MarketBookContract {

    interface View {
        fun recyclerView(): RecyclerView

        fun progressBar(value: Int)

        fun emptyTextVisible()
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()

        fun setUpData()

        fun loadMore()

        fun disposableClear()
    }
}