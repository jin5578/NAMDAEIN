package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager


class MainPresenter: MainContract.Presenter, TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private lateinit var view: MainContract.View
    private lateinit var context: Context

    private lateinit var mPagerAdapter : MainViewPagerAdapter


    override fun setView(view: MainContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpTabLayout() {
        view.tabLayout().addOnTabSelectedListener(this@MainPresenter)
    }

    override fun setUpViewPager(supportFragmentManager: FragmentManager) {
        mPagerAdapter = MainViewPagerAdapter(supportFragmentManager, view.tabLayout().tabCount)

        view.viewPager().apply {
            currentItem = 0
            offscreenPageLimit = view.tabLayout().tabCount
            adapter = mPagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabLayout()))
            setOnPageChangeListener(this@MainPresenter)
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

    //ViewPager ChangeListener
    override fun onPageSelected(position: Int) {
        when(position) {
            0 -> view.toolbarTitle("홈")

            1 -> view.toolbarTitle("장터")

            2 -> view.toolbarTitle("게시판")

            3 -> view.toolbarTitle("더보기")
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }
}