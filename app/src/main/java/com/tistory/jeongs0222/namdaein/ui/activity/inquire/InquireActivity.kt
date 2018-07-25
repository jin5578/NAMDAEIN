package com.tistory.jeongs0222.namdaein.ui.activity.inquire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import kotlinx.android.synthetic.main.activity_inquire.*

class InquireActivity : AppCompatActivity(), InquireContract.View {

    private lateinit var mPresenter: InquirePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquire)

        init()
    }

    private fun init() {
        mPresenter = InquirePresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpEditFunc()

        onClickEvent()
    }

    private fun onClickEvent() {
        inquire_confirm_imageView.setOnClickListener {
            mPresenter.setUpConfirmFunc()
        }
    }

    override fun content(): EditText {
        return inquire_content_editText
    }

    override fun count(length: Int) {
        inquire_count_textView.text = "(" + length + " / 250)"
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
