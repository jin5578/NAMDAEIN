package com.tistory.jeongs0222.namdaein.ui.activity.inquire

import android.content.Context
import android.widget.EditText
import android.widget.TextView


interface InquireContract {

    interface View {
        fun content(): EditText

        fun count(length: Int)

        fun confirmClickable(value: Int)

        fun viewFinish()

        fun toastMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpEditFunc()

        fun setUpConfirmFunc()

        fun disposableClear()
    }
}