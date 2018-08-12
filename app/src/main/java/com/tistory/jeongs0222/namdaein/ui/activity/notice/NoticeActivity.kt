package com.tistory.jeongs0222.namdaein.ui.activity.notice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity : AppCompatActivity(), NoticeContract.View {

    private lateinit var mPresenter: NoticePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        init()
    }

    private fun init() {
        mPresenter = NoticePresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()
    }

    override fun recyclerView(): RecyclerView {
        return notice_recyclerView
    }

    override fun onDestroy() {
        mPresenter.disposableClear()

        super.onDestroy()
    }


    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
