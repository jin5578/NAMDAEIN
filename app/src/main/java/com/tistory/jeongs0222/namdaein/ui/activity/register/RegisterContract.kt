package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context
import android.widget.EditText
import android.widget.TextView


interface RegisterContract {

    interface View {
        fun register_nickname(): EditText

        fun snackBar(message: String)

    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpValidate()
    }
}