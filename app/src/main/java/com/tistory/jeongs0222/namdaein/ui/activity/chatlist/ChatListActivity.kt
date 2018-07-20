package com.tistory.jeongs0222.namdaein.ui.activity.chatlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatListActivity : AppCompatActivity(), ChatListContract.View {

    private lateinit var mPresenter: ChatListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        init()
    }

    private fun init() {
        mPresenter = ChatListPresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpRecyclerView()
    }

    override fun recyclerView(): RecyclerView {
        return chatList_recyclerView
    }
}
