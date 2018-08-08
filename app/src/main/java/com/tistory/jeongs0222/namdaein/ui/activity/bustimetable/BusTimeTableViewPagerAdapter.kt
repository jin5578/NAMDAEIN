package com.tistory.jeongs0222.namdaein.ui.activity.bustimetable

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.capital.CapitalFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.cheonan.CheonanFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.pyeongtaek.PyeongtaekFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.seonghwan.SeonghwanFragment


class BusTimeTableViewPagerAdapter(fm: FragmentManager, internal val numOfTabs: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when(position) {
            0 -> SeonghwanFragment()

            1 -> CheonanFragment()

            2 -> CapitalFragment()

            3 -> PyeongtaekFragment()

            else -> null
        }
    }

    override fun getCount(): Int = numOfTabs
}