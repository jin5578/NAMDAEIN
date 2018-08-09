package com.tistory.jeongs0222.namdaein.ui.activity.subwaytimetable

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.subwaytimetable.holiday.HolidayFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.subwaytimetable.weekday.WeekdayFragment


class SubwayTimeTableViewPagerAdapter(fm: FragmentManager, internal val numOfTabs: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when(position) {
            0 -> WeekdayFragment()

            1 -> HolidayFragment()

            else -> null
        }
    }

    override fun getCount(): Int = numOfTabs
}