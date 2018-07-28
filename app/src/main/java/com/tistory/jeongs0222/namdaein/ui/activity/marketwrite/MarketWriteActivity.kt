package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.ArrayUtil
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

    override fun writeImageConstraint(): ConstraintLayout = write_image_constraint

    private fun getValue() {
        val intent = intent

        sort = intent.extras.getInt("sort")
        order = intent.extras.getInt("order")

        mPresenter.setUpBringMarket(sort, order) {
            write_spinner.isEnabled = false
            spinner().setText(ArrayUtil.marketSpinnerList[it.category])

            write_title_editText.setText(it.title)
            write_content_editText.setText(it.content)
            write_price_editText.setText(it.price)
        }
    }

    fun onClickEvent() {
        write_imageView.setOnClickListener { mPresenter.setUpMultiShow(supportFragmentManager) }

        write_confirm_imageView.setOnClickListener {
            confirmClickable(1)

            if (sort == 0) {
                mPresenter.setUpConfirmFunc()
            } else {
                mPresenter.setUpEditConfirmFunc(order)
            }

        }
    }

    override fun spinner(): MaterialBetterSpinner = write_spinner

    override fun selectedLinear(): LinearLayout = write_selectedLinear

    override fun confirmClickable(value: Int) {
        when (value) {
            0 -> write_confirm_imageView.isClickable = true

            1 -> write_confirm_imageView.isClickable = false
        }
    }

    override fun progressBar(value: Int) {
        when (value) {
            0 -> write_progressBar.visibility = View.VISIBLE

            1 -> write_progressBar.visibility = View.GONE
        }
    }

    override fun title(): EditText = write_title_editText

    override fun content(): EditText = write_content_editText

    override fun price(): EditText = write_price_editText

    override fun viewFinish() {
        finish()
    }

    override fun toastMessage(message: String) {
        Log.e("message", message)
        CustomToast(this).makeText(message, Toast.LENGTH_SHORT).show()
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
