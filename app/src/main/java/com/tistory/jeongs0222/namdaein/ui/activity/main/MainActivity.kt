package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter

    private lateinit var dbHelper: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        dbHelper = DBHelper(applicationContext, "USERINFO.db", null, 1)

        mPresenter = MainPresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpTabLayout()

        mPresenter.setUpViewPager(supportFragmentManager)
    }

    override fun tabLayout(): TabLayout = main_tabLayout

    override fun viewPager(): ViewPager = main_viewPager

    override fun toolbarTitle(title: String) {
        main_toolbar_title.text = title
    }

    override fun floatingButton(): FloatingActionButton = main_floating

    override fun floatingButtonVisible(value: Int) {
        when(value) {
            0 -> main_floating.visibility = View.VISIBLE

            1 -> main_floating.visibility = View.GONE
        }
    }

    override fun startActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
