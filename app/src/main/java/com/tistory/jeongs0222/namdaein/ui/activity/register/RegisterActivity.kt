package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.R.font.fonts
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var mPresenter: RegisterPresenter

    private lateinit var google_uId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        mPresenter = RegisterPresenter()

        mPresenter.setView(this, this)

        getValue()

        onClickEvent()
    }

    private fun getValue() {
        val intent = intent

        google_uId = intent.getStringExtra("google_uId")

        Log.e("Register Google", google_uId)
    }

    private fun onClickEvent() {
        register_validate_textView.setOnClickListener {
            mPresenter.setUpValidate()
        }
    }

    override fun register_nickname(): EditText {
        return register_nickname_editText
    }

    override fun snackBar(message: String) {
        val snackbar = Snackbar.make(register_entire_layout, message, Snackbar.LENGTH_SHORT)

        val textView = (snackbar.view).findViewById<TextView>(android.support.design.R.id.snackbar_text)

        textView.setTextSize(12F)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        } else {
            textView.gravity = Gravity.CENTER_HORIZONTAL
        }

        snackbar.show()
    }
}
