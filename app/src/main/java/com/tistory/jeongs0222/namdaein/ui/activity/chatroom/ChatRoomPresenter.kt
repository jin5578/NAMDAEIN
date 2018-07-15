package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.content.Context


class ChatRoomPresenter: ChatRoomContract.Presenter {

    private lateinit var view: ChatRoomContract.View
    private lateinit var context: Context

    override fun setView(view: ChatRoomContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}