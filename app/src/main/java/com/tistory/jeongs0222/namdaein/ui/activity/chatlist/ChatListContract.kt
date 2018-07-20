package com.tistory.jeongs0222.namdaein.ui.activity.chatlist

import android.content.Context
import android.support.v7.widget.RecyclerView


interface ChatListContract {

    interface View {
        fun recyclerView(): RecyclerView
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpRecyclerView()
    }
}