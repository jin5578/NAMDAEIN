package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tistory.jeongs0222.namdaein.R
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
    }

    override fun newsRecyclerView(): RecyclerView {
        return main_recyclerView
    }

    override fun dotsLinearLayout(): LinearLayout {
        return main_dots_linearLayout
    }
}
