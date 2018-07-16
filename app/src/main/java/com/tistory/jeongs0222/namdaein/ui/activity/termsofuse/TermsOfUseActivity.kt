package com.tistory.jeongs0222.namdaein.ui.activity.termsofuse

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.ui.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_terms_of_use.*

class TermsOfUseActivity : AppCompatActivity(), TermsOfUseContract.View {

    private lateinit var mPresenter: TermsOfUsePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_use)

        init()
    }

    private fun init() {
        mPresenter = TermsOfUsePresenter()

        mPresenter.setView(this, this)

        onClickEvent()
    }

    private fun onClickEvent() {
        terms_checkBox.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)

            startActivity(intent)

            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
