package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tistory.jeongs0222.namdaein.R
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import kotlinx.android.synthetic.main.activity_market_write.*

class MarketWriteActivity : AppCompatActivity(), MarketWriteContract.View {

    private lateinit var mPresenter: MarketWritePresenter

    private var sort: Int = 0
    private var order: Int = 0

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

        sort = intent.extras.getInt("sort")
        order = intent.extras.getInt("order")

        if(sort == 1) {
            market_image_constraint.visibility = View.GONE

            mPresenter.setUpBringMarket(order) { msg, it ->
                if(msg.equals("complete")) {
                    market_spinner.isEnabled = false
                    market_title_editText.setText(it.title)
                    market_content_editText.setText(it.content)
                    market_price_editText.setText(it.price)
                }
            }
        }
    }

    override fun spinner(): MaterialBetterSpinner {
        return market_spinner
    }
}
