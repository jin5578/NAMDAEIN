package com.tistory.jeongs0222.namdaein.ui.activity.subwaytimetable

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager


class SubwayTimeTablePresenter: SubwayTimeTableContract.Presenter, TabLayout.OnTabSelectedListener {

    private lateinit var view: SubwayTimeTableContract.View
    private lateinit var context: Context

    private lateinit var mAdapter: SubwayTimeTableViewPagerAdapter

    override fun setView(view: SubwayTimeTableContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@SubwayTimeTablePresenter)
    }

    override fun setUpViewPager(fragmentManager: FragmentManager) {
        mAdapter = SubwayTimeTableViewPagerAdapter(fragmentManager, view.tabLayout().tabCount)

        view.viewPager().apply {
            currentItem = 0
            offscreenPageLimit = view.tabLayout().tabCount
            adapter = mAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabLayout()))
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        view.viewPager().currentItem = tab!!.position
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }
}