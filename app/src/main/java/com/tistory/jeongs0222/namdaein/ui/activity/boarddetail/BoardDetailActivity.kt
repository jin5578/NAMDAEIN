package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context
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
import com.tistory.jeongs0222.namdaein.ui.activity.PictureViewPagerAdapter
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import kotlinx.android.synthetic.main.activity_board_detail.*


class BoardDetailActivity : AppCompatActivity(), BoardDetailContract.View {

    private lateinit var mPresenter: BoardDetailPresenter

    private var order = 0

    private var images: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        init()
    }

    private fun init() {
        mPresenter = BoardDetailPresenter()

        mPresenter.setView(this, this)

        getValue()

        initView()

        mPresenter.setUpRecyclerView()

        mPresenter.setUpCommentData()

        mPresenter.setUpCommentFunc()

        onClickEvent()
    }

    private fun getValue() {
        val intent = intent

        order = intent.extras.getInt("order")
    }

    private fun initView() {
        mPresenter.setUpInitData(order) {
            detail_title_textView.text = it.title
            detail_content_textView.text = it.content
            detail_nickname_textView.text = it.nickname
            detail_date_textView.text = it.date

            if (it.image0.isNotEmpty()) {
                images.add(it.image0)

                if (it.image1.isNotEmpty()) {
                    images.add(it.image1)

                    if (it.image2.isNotEmpty()) {
                        images.add(it.image2)
                    }
                }
            }

            mPresenter.pictureViewPager(images)

            mPresenter.addDots()

            progressBar(1)

        }
    }

    private fun onClickEvent() {
        detail_favorite_imageView.setOnClickListener {
            favoriteClickable(1)

            mPresenter.setUpFavoriteFunc()
        }

        detail_report_imageView.setOnClickListener {
            reportClickable(1)

            mPresenter.setUpReportFunc()
        }

        detail_send_textView.setOnClickListener {
            sendClickable(1)

            mPresenter.setUpSendFunc()
        }

        /*detail_entire_layout.setOnClickListener {
            Log.e("123", "123123123")
            val inputMethodManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(detail_send_editText.windowToken, 0)
        }*/ //Relative 위에 있어서 그런지 작동 X
    }

    override fun progressBar(value: Int) {
        when (value) {
            0 -> detail_progressBar.visibility = View.VISIBLE

            1 -> detail_progressBar.visibility = View.GONE
        }
    }

    override fun imageViewPager(): ViewPager = detail_viewPager

    override fun dotsLinearLayout(): LinearLayout = detail_dots_linearLayout

    override fun imageViewPagerVisible(value: Int) {
        when (value) {
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

    override fun recyclerView(): RecyclerView = detail_recyclerView

    override fun sendEditText(): EditText = detail_send_editText

    override fun sendVisible(value: Int) {
        when (value) {
            0 -> detail_send_textView.visibility = View.VISIBLE

            1 -> detail_send_textView.visibility = View.GONE
        }
    }

    override fun reportClickable(value: Int) {
        when (value) {
            0 -> detail_report_imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_sms_failed_pink_24dp))

            1 -> detail_report_imageView.isClickable = false
        }
    }

    override fun favoriteClickable(value: Int) {
        when (value) {
            0 -> detail_favorite_imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_pink_24dp))

            1 -> detail_favorite_imageView.isClickable = false
        }
    }

    override fun sendClickable(value: Int) {
        when (value) {
            0 -> detail_send_textView.isClickable = true

            1 -> detail_send_textView.isClickable = false
        }
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        mPresenter.disposableClear()

        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
