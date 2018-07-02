package com.tistory.jeongs0222.namdaein.ui.activity.base

import android.content.Context
import android.view.View


interface BaseView<T> {

    fun getView(): View
    fun getContext(): Context
}