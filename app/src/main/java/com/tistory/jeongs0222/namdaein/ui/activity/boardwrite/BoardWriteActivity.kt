package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.ArrayUtil
import com.tistory.jeongs0222.namdaein.utils.CustomToast
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

        initView()

        onClickEvent()
    }

    private fun getValue() {
        val intent = intent

        sort = intent.extras.getInt("sort")
        order = intent.extras.getInt("order")

        mPresenter.setUpBringBoard(sort, order) {
            write_spinner.isEnabled = false
            spinner().setText(ArrayUtil.boardSpinnerList[it.category])

            write_title_editText.setText(it.title)
            write_content_editText.setText(it.content)
        }
    }

    private fun initView() {
        write_price_constraint.visibility = View.GONE
        write_null.text = "게시판 글쓰기"
    }

    private fun onClickEvent() {
        write_imageView.setOnClickListener { mPresenter.setUpMultiShow(supportFragmentManager) }

        write_confirm_imageView.setOnClickListener {
            confirmClickable(1)

            if(sort == 0) {
                mPresenter.setUpConfirmFunc()
            } else {
                mPresenter.setUpEditConfirmFunc(order)
            }
        }
    }

    override fun spinner(): MaterialBetterSpinner = write_spinner

    override fun selectedLinear(): LinearLayout = write_selectedLinear

    override fun confirmClickable(value: Int) {

        when(value) {
            0 -> write_progressBar.visibility = View.VISIBLE

            1 -> write_progressBar.visibility = View.GONE
        }
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> write_progressBar.visibility = View.VISIBLE

            1 -> write_progressBar.visibility = View.GONE
        }
    }

    override fun title(): EditText = write_title_editText


    override fun content(): EditText = write_content_editText


    override fun viewFinish() {
        finish()
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT)
    }

    override fun writeImageConstraint(): ConstraintLayout = write_image_constraint

    override fun onDestroy() {
        mPresenter.disposableClear()

        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
