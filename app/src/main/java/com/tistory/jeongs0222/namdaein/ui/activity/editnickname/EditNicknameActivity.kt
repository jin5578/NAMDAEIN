package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tistory.jeongs0222.namdaein.R

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
    }
}
