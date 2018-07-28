package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context


class MainHomePresenter: MainHomeContract.Presenter {

    private lateinit var view: MainHomeContract.View
    private lateinit var context: Context


    override fun setView(view: MainHomeContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}