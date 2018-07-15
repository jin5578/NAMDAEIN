package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.content.Context


interface ChatRoomContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}