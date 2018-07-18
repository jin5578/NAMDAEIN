package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tistory.jeongs0222.namdaein.R

class MarketWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_write)

        init()
    }

    private fun init() {
        val intent = intent

        Log.e("sort", intent.extras.getInt("sort").toString())

        Log.e("order", intent.extras.getInt("order").toString())
    }
}
