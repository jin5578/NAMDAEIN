package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.LinearLayout
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_capital_detail.*

class CapitalDetailActivity : AppCompatActivity(), CapitalDetailContract.View {

    private lateinit var mPresenter: CapitalDetailPresenter

    private var order = 0

    private var images: MutableList<String> = ArrayList()


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

            images.add(it.locationImage1)

            if(it.locationImage2.isNotEmpty()) {
                images.add(it.locationImage2)

                if(it.locationImage3.isNotEmpty()) {
                    images.add(it.locationImage3)

                    if(it.locationImage4.isNotEmpty()) {
                        images.add(it.locationImage4)

                        if(it.locationImage5.isNotEmpty()) {
                            images.add(it.locationImage5)
                        }
                    }
                }
            }

            mPresenter.pictureViewPager(images)

            mPresenter.addDots()
        }
    }

    override fun imageViewPager(): ViewPager = capital_viewPager

    override fun dotsLinearLayout(): LinearLayout = capital_dots_linearLayout

}
