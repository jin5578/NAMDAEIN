package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context
import android.util.Log
import android.view.View


class MainPresenter: MainContract.Presenter {

    private lateinit var view: MainContract.View
    private lateinit var context: Context


    override fun setView(view: MainContract.View, context: Context) {
        this.view = view
        this.context = context
    }


}