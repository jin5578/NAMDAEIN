package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context
import android.support.v7.widget.RecyclerView


interface BoardFreeContract {

    interface View {
        fun recyclerView(): RecyclerView
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()
        fun setUpData()
    }
}