package com.tistory.jeongs0222.namdaein.ui.fragment.main.board

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardClubFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardFreeFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardLostFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardPromoteFragment


class MainBoardViewPagerAdapter(fm: FragmentManager, internal val numOfTabs: Int): FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return BoardFreeFragment()

            1 -> return BoardLostFragment()

            2 -> return BoardPromoteFragment()

            3 -> return BoardClubFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

}