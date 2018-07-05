package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context


class BoardFreePresenter: BoardFreeContract.Presenter {

    private lateinit var view: BoardFreeContract.View
    private lateinit var context: Context


    override fun setView(view: BoardFreeContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}