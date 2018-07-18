package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.PictureViewPagerAdapter
import com.tistory.jeongs0222.namdaein.ui.activity.chatroom.ChatRoomActivity
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import kotlinx.android.synthetic.main.activity_market_detail.*

class MarketDetailActivity : AppCompatActivity(), MarketDetailContract.View {

    private lateinit var mPresenter: MarketDetailPresenter

    private lateinit var dbHelper: DBHelper

    private var order = 0

    private lateinit var writtenUserGoogle_uId: String
    private lateinit var writtenUserNickname: String

    private var images: MutableList<String> = ArrayList()

    private lateinit var mAdapter: PictureViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_detail)

        init()
    }

    private fun init() {
        mPresenter = MarketDetailPresenter()

        mPresenter.setView(this, this)

        dbHelper = DBHelper(applicationContext, "USERINFO.db", null, 1)

        getValue()

        mPresenter.setUpInitData(order) { msg, it ->
            if(msg.equals("complete")) {
                writtenUserGoogle_uId = it.writtenUserkey
                writtenUserNickname = it.nickname
                detail_title_textView.text = it.title
                detail_price_textView.text = "₩ " + it.price
                detail_content_textView.text = it.content
                detail_nickname_textView.text = it.nickname
                detail_date_textView.text = it.date

                if(it.image0.isNotEmpty()) {
                    images.add(it.image0)

                    if(it.image1.isNotEmpty()) {
                        images.add(it.image1)

                        if(it.image2.isNotEmpty()) {
                            images.add(it.image2)

                            if(it.image3.isNotEmpty()) {
                                images.add(it.image3)

                                if(it.image4.isNotEmpty()) {
                                    images.add(it.image4)
                                }
                            }
                        }
                    }
                }

                pictureViewPager()

                addDots()

                progressBar(1)

            }
        }

        mPresenter.setUpRecyclerView()

        mPresenter.setUpCommentData()

        mPresenter.setUpCommentFunc()

        onClickEvent()
    }

    private fun getValue() {
        val intent = intent

        order = intent.extras.getInt("order")
    }

    private fun pictureViewPager() {
        mAdapter = PictureViewPagerAdapter(this, images)
        detail_viewPager.adapter = mAdapter
    }

    private fun addDots() {
        var dotsCount = images.size

        if(dotsCount > 0) {
            var dots = ArrayList<ImageView>()

            for(i in 0.. dotsCount - 1) {
                var imageView = ImageView(applicationContext)
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.dot_off))

                dots.add(imageView)

                var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(5, 0, 5, 0)

                detail_dots_linearLayout.addView(dots.get(i), params)
            }

            dots.get(0).setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.dot_on))

            detail_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageSelected(position: Int) {
                    for(j in 0.. dotsCount - 1) {
                        dots[j].setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.dot_off))
                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.dot_on))
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }
            })
        }
    }

    private fun onClickEvent() {
        detail_message_constraint.setOnClickListener {
            val intent = Intent(this, ChatRoomActivity::class.java)

            val intentExtra = ArrayList<String>()

            intentExtra.add(0, writtenUserGoogle_uId)
            intentExtra.add(1, writtenUserNickname)

            intent.putStringArrayListExtra("intentExtra", intentExtra)

            startActivity(intent)
        }

        detail_send_textView.setOnClickListener {
            if(writtenUserGoogle_uId != dbHelper.getGoogle_uId()) {
                sendClickable(1)

                mPresenter.setUpSendFunc()
            }
        }
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

    override fun recyclerView(): RecyclerView {
        return detail_recyclerView
    }

    override fun sendEditText(): EditText {
        return detail_send_editText
    }

    override fun sendVisible(value: Int) {
        when(value) {
            0 -> detail_send_textView.visibility = View.VISIBLE

            1 -> detail_send_textView.visibility = View.GONE
        }
    }

    override fun sendClickable(value: Int) {
        when(value) {
            0 -> detail_send_textView.isClickable = true

            1 -> detail_send_textView.isClickable = false
        }
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT).show()
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
