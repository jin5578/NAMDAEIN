package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.support.v7.widget.RecyclerView


interface MainHomeContract {

    interface View {
        fun newsRecyclerView(): RecyclerView

        fun bestRecyclerView(): RecyclerView
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitNews()

        fun setUpNewsData()

        fun setUpInitBest()

        fun setUpBestData()
    }
}