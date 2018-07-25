package com.tistory.jeongs0222.namdaein.ui.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
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

    override fun tabLayout(): TabLayout {
        return main_tabLayout
    }

    override fun viewPager(): ViewPager {
        return main_viewPager
    }

    override fun toolbarTitle(title: String) {
        main_toolbar_title.text = title
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
