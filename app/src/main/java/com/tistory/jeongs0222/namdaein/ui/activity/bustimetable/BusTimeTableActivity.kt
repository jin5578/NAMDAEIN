package com.tistory.jeongs0222.namdaein.ui.activity.bustimetable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager
import kotlinx.android.synthetic.main.activity_bus_time_table.*

class BusTimeTableActivity : AppCompatActivity(), BusTimeTableContract.View {

    private lateinit var mPresenter: BusTimeTablePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_time_table)

        init()
    }

    private fun init() {
        mPresenter = BusTimeTablePresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpTabLayout()

        mPresenter.setUpViewPager(supportFragmentManager)
    }

    override fun tabLayout(): TabLayout {
        return timeTable_tabLayout
    }

    override fun viewPager(): NonSwipeViewPager {
        return timeTable_viewPager
    }
}
