package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar


interface BoardFreeContract {

    interface View {
        fun recyclerView(): RecyclerView

        fun progressBar(value: Int)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()

        fun setUpData(loadValue: Int)

        fun loadMore()
    }
}