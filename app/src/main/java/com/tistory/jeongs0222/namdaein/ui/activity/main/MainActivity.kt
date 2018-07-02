package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, MainContract.View {


    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mPagerAdapter : MainViewPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }



    private fun init() {
        mPresenter = MainPresenter()

        mPresenter.setView(this, this)

        setUpTabLayout()

        setUpViewPager()
    }

    private fun setUpTabLayout() {

        main_tabLayout.addOnTabSelectedListener(this@MainActivity)

    }

    private fun setUpViewPager() {

        mPagerAdapter = MainViewPagerAdapter(supportFragmentManager, main_tabLayout.tabCount)

        main_viewPager.apply {
            adapter = mPagerAdapter
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
