package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context


interface BoardDetailContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}