package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context


class RegisterPresenter: RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View
    private lateinit var context: Context

    override fun setView(view: RegisterContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}