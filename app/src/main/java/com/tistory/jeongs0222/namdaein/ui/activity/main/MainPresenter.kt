package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.tistory.jeongs0222.namdaein.ui.activity.boardwrite.BoardWriteActivity
import com.tistory.jeongs0222.namdaein.ui.activity.marketwrite.MarketWriteActivity


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
            0 -> {
                view.toolbarTitle("홈")
                view.floatingButtonVisible(1)
            }

            1 -> {
                view.toolbarTitle("장터")
                view.floatingButtonVisible(0)
                view.floatingButton().setOnClickListener {
                    view.startActivity(MarketWriteActivity::class.java)
                }
            }

            2 -> {
                view.toolbarTitle("게시판")
                view.floatingButtonVisible(0)
                view.floatingButton().setOnClickListener {
                    view.startActivity(BoardWriteActivity::class.java)
                }
            }

            3 -> {
                view.toolbarTitle("더보기")
                view.floatingButtonVisible(1)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }
}