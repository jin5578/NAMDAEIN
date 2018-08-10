package com.tistory.jeongs0222.namdaein.ui.activity.calendar

import android.content.Context


interface CalendarContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}