package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tistory.jeongs0222.namdaein.R

class ChatRoomActivity : AppCompatActivity(), ChatRoomContract.View {

    private lateinit var mPresenter: ChatRoomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        init()
    }

    private fun init() {
        mPresenter = ChatRoomPresenter()

        mPresenter.setView(this, this)
    }
}
