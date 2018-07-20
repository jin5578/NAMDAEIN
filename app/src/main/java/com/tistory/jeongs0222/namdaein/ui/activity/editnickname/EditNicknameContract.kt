package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.content.Context


interface EditNicknameContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}