package com.tistory.jeongs0222.namdaein.ui.fragment.main.board

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_main_board_fragment.*

class MainBoardFragment : Fragment(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {


    private lateinit var mPagerAdapter: MainBoardViewPagerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_board_fragment, container, false)
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
        main_board_tabLayout.addOnTabSelectedListener(this@MainBoardFragment)

    }

    private fun setUpViewPager() {
        mPagerAdapter = MainBoardViewPagerAdapter(fragmentManager!!, main_board_tabLayout.tabCount)

        main_board_viewPager.apply {
            currentItem = 0
            offscreenPageLimit = main_board_tabLayout.tabCount
            adapter = mPagerAdapter
            //offscreenPageLimit = main_board_tabLayout.tabCount
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_board_tabLayout))
        }

    }

    //TabBar SelectedListener
    override fun onTabSelected(tab: TabLayout.Tab?) {
        main_board_viewPager.currentItem = tab!!.position
    }
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    //ViewPager ChangeListener
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

}
