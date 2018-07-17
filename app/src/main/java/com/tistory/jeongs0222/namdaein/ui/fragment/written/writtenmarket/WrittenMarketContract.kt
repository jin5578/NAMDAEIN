package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket

import android.content.Context
import android.support.v7.widget.RecyclerView


interface WrittenMarketContract {

    interface View {
        fun recyclerView(): RecyclerView

        fun emptyTextVisible()
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()

        fun setUpData()

        fun disposableClear()

    }
}