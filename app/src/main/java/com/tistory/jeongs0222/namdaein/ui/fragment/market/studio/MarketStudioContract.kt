package com.tistory.jeongs0222.namdaein.ui.fragment.market.studio

import android.content.Context
import android.support.v7.widget.RecyclerView


interface MarketStudioContract {

    interface View {
        fun recyclerView(): RecyclerView

        fun progressBar(value: Int)

        fun emptyTextVisible()
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()

        fun setUpData(loadValue: Int)

        fun loadMore()

        fun disposableClear()
    }
}