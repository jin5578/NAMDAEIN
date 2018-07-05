package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Context


interface LoginContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)

        //fun setUpGoogleLogin()
    }
}