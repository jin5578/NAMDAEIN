package com.tistory.jeongs0222.namdaein.ui.activity.push

import android.content.Context
import android.widget.CompoundButton
import com.tistory.jeongs0222.namdaein.model.DBHelper


class PushPresenter: PushContract.Presenter, CompoundButton.OnCheckedChangeListener {

    private lateinit var view: PushContract.View
    private lateinit var context: Context

    private lateinit var dbHelper: DBHelper


    override fun setView(view: PushContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun initPush() {
        if(dbHelper.getPush() == "on") {
            view.switchOnOff(0)  //켜짐
        } else {
            view.switchOnOff(1)  //꺼짐
        }
    }

    override fun pushChangeFun() {
        view.switch().setOnCheckedChangeListener(this@PushPresenter)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        if(isChecked == true) {
            dbHelper.pushUpdate("on")
        } else {
            dbHelper.pushUpdate("off")
        }
    }
}