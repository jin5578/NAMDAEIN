package com.tistory.jeongs0222.namdaein.ui.activity.notice

import android.content.Context
import android.support.v7.widget.RecyclerView


interface NoticeContract {

    interface View {
        fun recyclerView(): RecyclerView
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()

        fun setUpData()

        fun disposableClear()
    }
}