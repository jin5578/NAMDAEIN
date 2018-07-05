package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context


interface BoardFreeContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}