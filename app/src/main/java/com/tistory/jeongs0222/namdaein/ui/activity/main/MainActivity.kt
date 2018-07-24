package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mPagerAdapter : MainViewPagerAdapter

    private lateinit var dbHelper: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        dbHelper = DBHelper(applicationContext, "USERINFO.db", null, 1)

        Log.e("nickname", dbHelper.getNickname())
        Log.e("google_uid", dbHelper.getGoogle_uId())
        Log.e("push", dbHelper.getPush())
        Log.e("connectModel", dbHelper.getConnectModel())

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
            currentItem = 0
            offscreenPageLimit = main_tabLayout.tabCount
            adapter = mPagerAdapter
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
            0 -> main_toolbar_title.text = "홈"

            1 -> main_toolbar_title.text = "장터"

            2 -> main_toolbar_title.text = "게시판"
            
            3 -> main_toolbar_title.text = "더보기"
        }
    }
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
