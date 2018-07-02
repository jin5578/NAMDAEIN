package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context


interface MainContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}