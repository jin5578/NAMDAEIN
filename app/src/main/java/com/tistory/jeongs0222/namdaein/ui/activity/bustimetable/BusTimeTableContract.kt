package com.tistory.jeongs0222.namdaein.ui.activity.bustimetable

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager


interface BusTimeTableContract {

    interface View {
        fun tabLayout(): TabLayout

        fun viewPager(): com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpTabLayout()

        fun setUpViewPager(fragmentManager: FragmentManager)

    }
}