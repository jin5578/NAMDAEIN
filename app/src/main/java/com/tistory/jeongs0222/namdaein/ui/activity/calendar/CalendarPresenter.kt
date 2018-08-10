package com.tistory.jeongs0222.namdaein.ui.activity.calendar

import android.content.Context


class CalendarPresenter: CalendarContract.Presenter {

    private lateinit var view: CalendarContract.View
    private lateinit var context: Context

    override fun setView(view: CalendarContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}