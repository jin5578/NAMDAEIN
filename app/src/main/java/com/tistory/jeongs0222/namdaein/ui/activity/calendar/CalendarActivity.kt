package com.tistory.jeongs0222.namdaein.ui.activity.calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tistory.jeongs0222.namdaein.R

class CalendarActivity : AppCompatActivity(), CalendarContract.View {

    private lateinit var mPresenter: CalendarPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        init()
    }

    private fun init() {
        mPresenter = CalendarPresenter()

        mPresenter.setView(this, this)

    }
}

