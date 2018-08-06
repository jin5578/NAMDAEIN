package com.tistory.jeongs0222.namdaein.ui.activity.push

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_push.*

class PushActivity : AppCompatActivity(), PushContract.View {

    private lateinit var mPresenter: PushPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        init()
    }

    private fun init() {
        mPresenter = PushPresenter()

        mPresenter.setView(this, this)

        mPresenter.initPush()

        mPresenter.pushChangeFun()
    }

    override fun switch(): Switch {
        return push_switch
    }

    override fun switchOnOff(value: Int) {
        when(value) {
            0 -> push_switch.isChecked = true

            1 -> push_switch.isChecked = false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
