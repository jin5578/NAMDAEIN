package com.tistory.jeongs0222.namdaein.ui.fragment.main.board

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager


class MainBoardPresenter: MainBoardContract.Presenter, TabLayout.OnTabSelectedListener {

    private lateinit var view: MainBoardContract.View
    private lateinit var context: Context

    private lateinit var mPagerAdapter: MainBoardViewPagerAdapter


    override fun setView(view: MainBoardContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@MainBoardPresenter)
    }

    override fun setUpViewPager(fragmentManager: FragmentManager) {
        mPagerAdapter = MainBoardViewPagerAdapter(fragmentManager!!, view.tabLayout().tabCount)

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