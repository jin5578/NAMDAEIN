package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.EditText


interface ChatRoomContract {

    interface View {
        fun recyclerView(): RecyclerView

        fun sendEditText(): EditText

        fun sendVisible(value: Int)

        fun sendClickable(value: Int)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun checkRoom(writtenUserGoogle_uId: ArrayList<String>)

        fun setUpMessageFunc()

        fun setUpSendFunc()
    }
}