package com.tistory.jeongs0222.namdaein.ui.activity.written

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket.WrittenMarketFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenboard.WrittenBoardFragment


class WrittenPagerAdapter(fm: FragmentManager, internal var numOfTabs: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> return WrittenMarketFragment()

            1 -> return WrittenBoardFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

}