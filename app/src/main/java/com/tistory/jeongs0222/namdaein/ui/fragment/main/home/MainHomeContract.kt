package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout


interface MainHomeContract {

    interface View {
        fun newsRecyclerView(): RecyclerView

        fun dotsLinearLayout(): LinearLayout
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitNews()

        fun setUpNewsData()
    }
}