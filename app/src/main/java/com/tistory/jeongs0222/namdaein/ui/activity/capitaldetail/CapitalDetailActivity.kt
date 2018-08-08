package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_capital_detail.*

class CapitalDetailActivity : AppCompatActivity(), CapitalDetailContract.View {

    private lateinit var mPresenter: CapitalDetailPresenter

    private var order = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capital_detail)

        init()
    }

    private fun init() {

        mPresenter = CapitalDetailPresenter()

        mPresenter.setView(this, this)

        getValue()

        initView()



    }

    private fun getValue() {
        val intent = intent

        order = intent.extras.getInt("order")
    }

    private fun initView() {
        mPresenter.setUpInitData(order) {
            capital_location_textView.text = it.location
            capital_boardingGate_textView.text = it.boardingGate
            capital_price_textView.text = it.price.toString()+"원"
            capital_school_textView.text = it.school
            capital_home_textView.text = it.home
            capital_etc_textView.text = it.etc
        }
    }
}
