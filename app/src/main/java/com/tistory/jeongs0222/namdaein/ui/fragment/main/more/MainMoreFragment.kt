package com.tistory.jeongs0222.namdaein.ui.fragment.main.more

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.written.WrittenActivity
import kotlinx.android.synthetic.main.activity_main_more_fragment.*

class MainMoreFragment : Fragment() {

    private lateinit var dbHelper: DBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_more_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        dbHelper = DBHelper(context!!, "USERINFO.db", null, 1)

        more_nickname_textView.text = dbHelper.getNickname()
        more_connect_textView.text = dbHelper.getConnectModel()

        onClickEvent()
    }

    private fun onClickEvent() {
        more_written_textView.setOnClickListener {
            val intent = Intent(activity, WrittenActivity::class.java)

            startActivity(intent)
        }
    }

}
