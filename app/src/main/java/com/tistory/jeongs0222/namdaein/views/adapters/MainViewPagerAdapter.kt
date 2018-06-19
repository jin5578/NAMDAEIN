package com.tistory.jeongs0222.namdaein.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.views.fragments.MainBoardFragment
import com.tistory.jeongs0222.namdaein.views.fragments.MainHomeFragment
import com.tistory.jeongs0222.namdaein.views.fragments.MainMarketFragment
import com.tistory.jeongs0222.namdaein.views.fragments.MainMoreFragment


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
