package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tistory.jeongs0222.namdaein.R
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import kotlinx.android.synthetic.main.activity_write.*

class BoardWriteActivity : AppCompatActivity(), BoardWriteContract.View {

    private lateinit var mPresenter: BoardWritePresenter

    private var sort: Int = 0
    private var order: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        init()
    }

    private fun init() {
        mPresenter = BoardWritePresenter()

        mPresenter.setView(this, this)

        getValue()

        mPresenter.setUpSpinnerFunc()

        write_price_constraint.visibility = View.GONE


    }

    private fun getValue() {
        val intent = intent

        sort = intent.extras.getInt("sort")
        order = intent.extras.getInt("order")

        if(sort == 1) {
            write_image_constraint.visibility = View.GONE

            mPresenter.setUpBringBoard(order) { msg, it ->
                if(msg.equals("complete")) {
                    write_spinner.isEnabled = false

                    write_title_editText.setText(it.title)
                    write_content_editText.setText(it.content)
                }
            }
        }
    }

    override fun viewFinish() {
        finish()
    }

    override fun spinner(): MaterialBetterSpinner {
        return write_spinner
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
