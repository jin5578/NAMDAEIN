package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
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

                pictureViewPager()

                addDots()

                progressBar(1)
            }
        }

        mPresenter.setUpRecyclerView()

        mPresenter.setUpCommentData()

        mPresenter.setUpCommentFunc()

        detail_favorite_imageView.setOnClickListener {
            favoriteClickable(1)

            mPresenter.setUpFavoriteFunc()
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

    override fun favoriteClickable(value: Int) {
        when(value) {
            0 -> detail_favorite_imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_pink_24dp))

            1 -> detail_favorite_imageView.isClickable = false
        }
    }

    override fun sendClickable(value: Int) {
        when(value) {
            0 -> detail_send_textView.isClickable = true

            1 -> detail_send_textView.isClickable = false
        }
    }

    override fun snackBar(message: String) {
        val snackbar = Snackbar.make(detail_entire_layout, message, Snackbar.LENGTH_SHORT)

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
