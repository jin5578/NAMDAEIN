package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.CustomToast
import kotlinx.android.synthetic.main.activity_edit_nickname.*

class EditNicknameActivity : AppCompatActivity(), EditNicknameContract.View {

    private lateinit var mPresenter: EditNicknamePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nickname)

        init()
    }

    private fun init() {
        mPresenter = EditNicknamePresenter()

        mPresenter.setView(this, this)

        mPresenter.setUpNicknameFunc()

        onClickEvent()
    }

    private fun onClickEvent() {
        edit_validate_textView.setOnClickListener {
            mPresenter.setUpValidate()
        }

        edit_confirm_imageView.setOnClickListener {
            confirmClickable(0)

            mPresenter.setUpNicknameUpdate()
        }
    }

    override fun edit_nickname(): EditText = edit_nickname_editText

    override fun confirmClickable(value: Int) {
        when (value) {
            0 -> edit_confirm_imageView.isClickable = false

            1 -> edit_confirm_imageView.isClickable = true
        }
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT).show()
    }

    override fun viewFinish() {
        finish()
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
