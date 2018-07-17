package com.tistory.jeongs0222.namdaein.ui.activity.written

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager


interface WrittenContract {

    interface View {
        fun tabLayout(): TabLayout

        fun viewPager(): NonSwipeViewPager
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpTabLayout()

        fun setUpViewPager(supportFragmentManager: FragmentManager)
    }
}