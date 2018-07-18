package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.tistory.jeongs0222.namdaein.R
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import kotlinx.android.synthetic.main.activity_market_write.*

class MarketWriteActivity : AppCompatActivity(), MarketWriteContract.View {

    private lateinit var mPresenter: MarketWritePresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_write)

        init()
    }

    private fun init() {
        mPresenter = MarketWritePresenter()

        mPresenter.setView(this, this)

        getValue()

        mPresenter.setUpSpinnerFunc()
    }

    private fun getValue() {
        val intent = intent

        Log.e("sort", intent.extras.getInt("sort").toString())

        Log.e("order", intent.extras.getInt("order").toString())
    }

    override fun spinner(): MaterialBetterSpinner {
        return market_spinner
    }
}
