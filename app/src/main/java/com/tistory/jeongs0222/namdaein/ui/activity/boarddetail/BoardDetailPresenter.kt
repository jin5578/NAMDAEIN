package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context


class BoardDetailPresenter: BoardDetailContract.Presenter {

    private lateinit var view: BoardDetailContract.View
    private lateinit var context: Context

    override fun setView(view: BoardDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}