package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context
import android.widget.EditText


interface RegisterContract {

    interface View {
        fun register_nickname(): EditText

        fun toastMessage(message: String)

        fun startActivity(activityClass: Class<*>)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpValidate()

        fun setUpSignIn(connectModel: String)

        fun disposableClear()
    }
}