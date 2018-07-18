package com.tistory.jeongs0222.namdaein.ui.activity.written

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager


class WrittenPresenter: WrittenContract.Presenter, TabLayout.OnTabSelectedListener {

    private lateinit var view: WrittenContract.View
    private lateinit var context: Context

    private lateinit var mPagerAdapter: WrittenPagerAdapter

    override fun setView(view: WrittenContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@WrittenPresenter)
    }

    override fun setUpViewPager(supportFragmentManager: FragmentManager) {
        mPagerAdapter = WrittenPagerAdapter(supportFragmentManager, view.tabLayout().tabCount)

        view.viewPager().apply {
            currentItem = 0
            offscreenPageLimit = view.tabLayout().tabCount
            adapter = mPagerAdapter
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