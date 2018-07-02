package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {


    private lateinit var pagerAdapter : MainViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }

    private fun init() {

        setUpTabLayout()

        setUpViewPager()


    }

    private fun setUpTabLayout() {

        main_tabLayout.apply {
            addTab(main_tabLayout.newTab().setText("홈"))
            addTab(main_tabLayout.newTab().setText("장터"))
            addTab(main_tabLayout.newTab().setText("게시판"))
            addTab(main_tabLayout.newTab().setText("더보기"))

            addOnTabSelectedListener(this@MainActivity)
        }
    }

    private fun setUpViewPager() {

        pagerAdapter = MainViewPagerAdapter(supportFragmentManager, main_tabLayout.tabCount)

        main_viewPager.apply {
            adapter = pagerAdapter
            currentItem = 0
            offscreenPageLimit = main_tabLayout.tabCount
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tabLayout))
            setOnPageChangeListener(this@MainActivity)
        }
    }


    //TabBar SelectedListener
    override fun onTabSelected(tab: TabLayout.Tab?) {
        main_viewPager.currentItem = tab!!.position
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }


    //ViewPager ChangeListener
    override fun onPageSelected(position: Int) {
        when(position) {
            0 -> {
                runOnUiThread { main_toolbar_title.text = "홈" }
            }

            1 -> {
                runOnUiThread { main_toolbar_title.text = "장터" }
            }

            2 -> {
                runOnUiThread { main_toolbar_title.text = "게시판" }
            }

            3 -> {
                runOnUiThread { main_toolbar_title.text = "더보기" }
            }
        }
    }
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }


}
