package com.tistory.jeongs0222.namdaein.ui.fragment.main.board

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.NonSwipeViewPager
import kotlinx.android.synthetic.main.activity_main_board_fragment.*


class MainBoardFragment : Fragment(), MainBoardContract.View {

    private lateinit var mPresenter: MainBoardPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        mPresenter = MainBoardPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpTabLayout()

        mPresenter.setUpViewPager(fragmentManager!!)
    }

    override fun tabLayout(): TabLayout {
        return main_board_tabLayout
    }

    override fun viewPager(): NonSwipeViewPager {
        return main_board_viewPager
    }

}
