package com.tistory.jeongs0222.namdaein.ui.activity.push

import android.content.Context
import android.widget.Switch


interface PushContract {

    interface View {
        fun switch(): Switch

        fun switchOnOff(value: Int)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun initPush()

        fun pushChangeFun()
    }
}