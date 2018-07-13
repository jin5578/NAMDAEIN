package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tistory.jeongs0222.namdaein.R

class MarketDetailActivity : AppCompatActivity(), MarketDetailContract.View {

    private lateinit var mPresenter: MarketDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_detail)

        init()
    }

    private fun init() {
        mPresenter = MarketDetailPresenter()

        mPresenter.setView(this, this)
    }
}
