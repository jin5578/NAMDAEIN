package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var mPresenter: RegisterPresenter

    private lateinit var connectModel: String


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

        connectModel = intent.getStringExtra("connectModel")
    }
    private fun onClickEvent() {
        register_validate_textView.setOnClickListener {
            mPresenter.setUpValidate()
        }

        register_confirm_ImageView.setOnClickListener {
            mPresenter.setUpSignIn(connectModel)
        }
    }

    override fun register_nickname(): EditText {
        return register_nickname_editText
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT).show()
    }

    override fun startActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)

        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

    override fun onDestroy() {
        mPresenter.disposableClear()

        super.onDestroy()
    }
}
