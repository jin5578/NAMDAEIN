package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import kotlinx.android.synthetic.main.activity_write.*

class MarketWriteActivity : AppCompatActivity(), MarketWriteContract.View {

    private lateinit var mPresenter: MarketWritePresenter

    private var sort: Int = 0
    private var order: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

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
            write_image_constraint.visibility = View.GONE

            mPresenter.setUpBringMarket(order) { msg, it ->
                if(msg.equals("complete")) {
                    write_spinner.isEnabled = false

                    write_title_editText.setText(it.title)
                    write_content_editText.setText(it.content)
                    write_price_editText.setText(it.price)
                }
            }
        }
    }

    fun onClickEvent() {
        write_imageView.setOnClickListener {
            mPresenter.setUpMultiShow(supportFragmentManager)
        }

        write_confirm_imageView.setOnClickListener {
            confirmClickable(1)

            if(sort == 0) {

            } else {
                mPresenter.setUpEditConfirmFunc(order)
            }

        }
    }

    override fun spinner(): MaterialBetterSpinner {
        return write_spinner
    }

    override fun selectedLinear(): LinearLayout {
        return write_selectedLinear
    }

    override fun confirmClickable(value: Int) {
        when(value) {
            0 -> write_confirm_imageView.isClickable = true

            1 -> write_confirm_imageView.isClickable = false
        }
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> write_progressBar.visibility = View.VISIBLE

            1 -> write_progressBar.visibility = View.GONE
        }
    }

    override fun title(): EditText {
        return write_title_editText
    }

    override fun content(): EditText {
        return write_content_editText
    }

    override fun price(): EditText {
        return write_price_editText
    }

    override fun viewFinish() {
        finish()
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT)
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
