package com.tistory.jeongs0222.namdaein.ui.fragment.main.board

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.board.club.BoardClubFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.free.BoardFreeFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.lost.BoardLostFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.board.promote.BoardPromoteFragment


class MainBoardViewPagerAdapter(fm: FragmentManager, internal val numOfTabs: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> BoardFreeFragment()

            1 -> BoardLostFragment()

            2 -> BoardPromoteFragment()

            3 -> BoardClubFragment()

            else -> null
        }
    }

    override fun getCount(): Int = numOfTabs
}