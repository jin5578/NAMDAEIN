package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.content.Context


class EditNicknamePresenter: EditNicknameContract.Presenter {

    lateinit var view: EditNicknameContract.View
    lateinit var context: Context

    override fun setView(view: EditNicknameContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}