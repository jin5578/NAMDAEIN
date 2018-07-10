package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tistory.jeongs0222.namdaein.R

class BoardDetailActivity : AppCompatActivity(), BoardDetailContract.View {

    private lateinit var mPresenter: BoardDetailPresenter

    private var order = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        init()
    }

    private fun init() {
        mPresenter = BoardDetailPresenter()

        mPresenter.setView(this, this)

        getValue()
    }

    private fun getValue() {
        val intent = intent

        order = intent.extras.getInt("order")

        Log.e("order", order.toString())
    }
}
