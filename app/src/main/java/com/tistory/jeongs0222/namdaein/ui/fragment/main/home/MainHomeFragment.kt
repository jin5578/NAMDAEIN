package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.ui.activity.bustimetable.BusTimeTableActivity
import com.tistory.jeongs0222.namdaein.ui.activity.subwaytimetable.SubwayTimeTableActivity
import kotlinx.android.synthetic.main.activity_main_home_fragment.*


class MainHomeFragment : Fragment(), MainHomeContract.View {

    private lateinit var mPresenter: MainHomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = MainHomePresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpInitNews()

        mPresenter.setUpNewsData()

        mPresenter.setUpInitBest()

        mPresenter.setUpBestData()

        onClickEvent()
    }

    private fun onClickEvent() {
        main_bus_imageView.setOnClickListener {
            startActivity(BusTimeTableActivity::class.java)
        }

        main_subway_imageView.setOnClickListener {
            startActivity(SubwayTimeTableActivity::class.java)
        }

        main_calendar_imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nsu.ac.kr/?m1=page%25&menu_id=218%25"))

            startActivity(intent)
        }
    }

    private fun startActivity(activityClass: Class<*>) {
        val intent = Intent(activity, activityClass)

        startActivity(intent)
    }

    override fun newsRecyclerView(): RecyclerView {
        return main_recyclerView
    }

    override fun bestRecyclerView(): RecyclerView {
        return main_best_recyclerView
    }
}
