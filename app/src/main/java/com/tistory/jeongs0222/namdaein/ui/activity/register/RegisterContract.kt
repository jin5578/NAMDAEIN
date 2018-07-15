package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context


interface RegisterContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}