package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_market_write.*

class MarketWriteActivity : AppCompatActivity() {

    private val spinnerList = arrayOf("여성의류", "남성의류", "패션잡화", "뷰티", "도서", "티켓", "가전제품", "생활", "원룸", "기타")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_write)

        init()
    }

    private fun init() {
        val intent = intent

        Log.e("sort", intent.extras.getInt("sort").toString())

        Log.e("order", intent.extras.getInt("order").toString())

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinnerList)

        market_spinner.setAdapter(arrayAdapter)
    }
}
