package com.tistory.jeongs0222.namdaein.ui.activity.bustimetable

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager


class BusTimeTablePresenter: BusTimeTableContract.Presenter, TabLayout.OnTabSelectedListener {

    private lateinit var view: BusTimeTableContract.View
    private lateinit var context: Context

    private lateinit var mAdapter: BusTimeTableViewPagerAdapter


    override fun setView(view: BusTimeTableContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@BusTimeTablePresenter)
    }

    //TabBar SelectedListener
    override fun onTabSelected(tab: TabLayout.Tab?) {
        view.viewPager().currentItem = tab!!.position
    }

    override fun setUpViewPager(fragmentManager: FragmentManager) {
        mAdapter = BusTimeTableViewPagerAdapter(fragmentManager, view.tabLayout().tabCount)

        view.viewPager().apply {
            currentItem = 0
            offscreenPageLimit = view.tabLayout().tabCount
            adapter = mAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabLayout()))
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }
}