package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
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

        onClickEvent()
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

    fun onClickEvent() {
        market_confirm_imageView.setOnClickListener {
            confirmClickable(1)
            if(sort == 0) {

            } else {
                mPresenter.setUpEditConfirmFunc(order)
            }

        }
    }

    override fun spinner(): MaterialBetterSpinner {
        return market_spinner
    }

    override fun confirmClickable(value: Int) {
        when(value) {
            0 -> market_confirm_imageView.isClickable = true

            1 -> market_confirm_imageView.isClickable = false
        }
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> market_progressBar.visibility = View.VISIBLE

            1 -> market_progressBar.visibility = View.GONE
        }
    }

    override fun title(): EditText {
        return market_title_editText
    }

    override fun content(): EditText {
        return market_content_editText
    }

    override fun price(): EditText {
        return market_price_editText
    }

    override fun viewFinish() {
        finish()
    }

    override fun snackBar(message: String) {
        val snackbar = Snackbar.make(market_entire_layout, message, Snackbar.LENGTH_SHORT)

        snackbar.show()
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.disposableClear()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
