package com.tistory.jeongs0222.namdaein.ui.activity.chatlist

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper


class ChatListPresenter: ChatListContract.Presenter {

    private lateinit var view: ChatListContract.View
    private lateinit var context: Context

    private lateinit var mAdapter: ChatListAdapter

    override fun setView(view: ChatListContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = ChatListAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter

            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }
}