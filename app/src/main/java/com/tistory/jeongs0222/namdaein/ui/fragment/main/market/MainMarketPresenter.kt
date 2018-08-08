package com.tistory.jeongs0222.namdaein.ui.fragment.main.market

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager


class MainMarketPresenter: MainMarketContract.Presenter, TabLayout.OnTabSelectedListener {

    private lateinit var view: MainMarketContract.View
    private lateinit var context: Context

    private lateinit var mPagerAdapter: MainMarketViewPagerAdapter


    override fun setView(view: MainMarketContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@MainMarketPresenter)
    }

    override fun setUpViewPager(fragmentManager: FragmentManager) {
        mPagerAdapter = MainMarketViewPagerAdapter(fragmentManager, view.tabLayout().tabCount)

        view.viewPager().apply {
            currentItem = 0
            offscreenPageLimit = view.tabLayout().tabCount
            adapter = mPagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabLayout()))
        }
    }

    //TabBar SelectedListener
    override fun onTabSelected(tab: TabLayout.Tab?) {
        view.viewPager().currentItem = tab!!.position
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }
}