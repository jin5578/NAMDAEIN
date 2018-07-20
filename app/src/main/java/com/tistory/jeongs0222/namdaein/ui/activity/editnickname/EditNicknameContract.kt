package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.content.Context
import android.widget.EditText


interface EditNicknameContract {

    interface View {
        fun edit_nickname(): EditText

        fun toastMessage(message: String)

        fun viewFinish()
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpNicknameFunc()

        fun setUpValidate()

        fun setUpNicknameUpdate()

        fun disposableClear()
    }
}