package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.ui.activity.PictureViewPagerAdapter
import kotlinx.android.synthetic.main.activity_board_detail.*

class BoardDetailActivity : AppCompatActivity(), BoardDetailContract.View {

    private lateinit var mPresenter: BoardDetailPresenter

    private var order = 0

    private var images: MutableList<String> = ArrayList()

    private lateinit var mAdapter: PictureViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        init()
    }

    private fun init() {
        mPresenter = BoardDetailPresenter()

        mPresenter.setView(this, this)

        getValue()

        mPresenter.setUpInitData(order) { msg, it ->
            if(msg.equals("complete")) {
                detail_title_textView.text = it.title
                detail_content_textView.text = it.content
                detail_nickname_textView.text = it.nickname
                detail_date_textView.text = it.date

                if(it.image0.isNotEmpty()) {
                    images.add(it.image0)

                    if(it.image1.isNotEmpty()) {
                        images.add(it.image1)

                        if(it.image2.isNotEmpty()) {
                            images.add(it.image2)
                        }
                    }
                }

                pictureViewPager(images)

                progressBar(1)
            }

        }
    }

    private fun getValue() {
        val intent = intent

        order = intent.extras.getInt("order")

        Log.e("order", order.toString())
    }

    private fun pictureViewPager(images: MutableList<String>) {
        mAdapter = PictureViewPagerAdapter(this, images)
        detail_viewPager.adapter = mAdapter
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> detail_progressBar.visibility = View.VISIBLE

            1 -> detail_progressBar.visibility = View.GONE
        }
    }

    override fun imageViewPagerVisible(value: Int) {
        when(value) {
            0 -> {
                detail_viewPager.visibility = View.VISIBLE
                detail_dots_linearLayout.visibility = View.VISIBLE
            }

            1 -> {
                detail_viewPager.visibility = View.GONE
                detail_dots_linearLayout.visibility = View.GONE
            }
        }
    }
}
