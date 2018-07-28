package com.tistory.jeongs0222.namdaein.ui.activity.written

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager
import kotlinx.android.synthetic.main.activity_written.*

class WrittenActivity : AppCompatActivity(), WrittenContract.View {

    private lateinit var mPresenter: WrittenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_written)

        init()
    }

    private fun init() {
        mPresenter = WrittenPresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpTabLayout()

        mPresenter.setUpViewPager(supportFragmentManager)
    }

    override fun tabLayout(): TabLayout = written_tabLayout

    override fun viewPager(): NonSwipeViewPager = written_viewPager

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
