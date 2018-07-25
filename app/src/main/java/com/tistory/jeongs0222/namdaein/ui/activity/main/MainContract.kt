package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager


interface MainContract {

    interface View {
        fun tabLayout(): TabLayout

        fun viewPager(): ViewPager

        fun toolbarTitle(title: String)

        fun floatingButton(): FloatingActionButton

        fun floatingButtonVisible(value: Int)

        fun startActivity(activityClass: Class<*>)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpTabLayout()

        fun setUpViewPager(supportFragmentManager: FragmentManager)
    }
}