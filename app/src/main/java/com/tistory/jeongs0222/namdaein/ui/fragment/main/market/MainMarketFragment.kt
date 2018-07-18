package com.tistory.jeongs0222.namdaein.ui.fragment.main.market

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_main_market_fragment.*


class MainMarketFragment : Fragment(), TabLayout.OnTabSelectedListener {


    private lateinit var mPagerAdapter: MainMarketViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        setUpTabLayout()

        setUpViewPager()
    }

    private fun setUpTabLayout() {
        main_market_tabLayout.addOnTabSelectedListener(this@MainMarketFragment)
    }

    private fun setUpViewPager() {
        mPagerAdapter = MainMarketViewPagerAdapter(fragmentManager!!, main_market_tabLayout.tabCount)

        main_market_viewPager.apply {
            currentItem = 0
            offscreenPageLimit = main_market_tabLayout.tabCount
            adapter = mPagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_market_tabLayout))
        }
    }

    //TabBar SelectedListener
    override fun onTabSelected(tab: TabLayout.Tab?) {
        main_market_viewPager.currentItem = tab!!.position
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

}

