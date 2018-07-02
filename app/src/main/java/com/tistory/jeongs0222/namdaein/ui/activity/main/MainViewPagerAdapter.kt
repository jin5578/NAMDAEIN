package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.main.board.MainBoardFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.main.MainHomeFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.main.MainMarketFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.main.MainMoreFragment


class MainViewPagerAdapter(fm: FragmentManager, internal var numOfTabs: Int) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return MainHomeFragment()

            1 -> return MainMarketFragment()

            2 -> return MainBoardFragment()

            3 -> return MainMoreFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

}
