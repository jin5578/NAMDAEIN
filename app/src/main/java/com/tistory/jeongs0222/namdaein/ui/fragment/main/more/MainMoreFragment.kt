package com.tistory.jeongs0222.namdaein.ui.fragment.main.more

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.chatlist.ChatListActivity
import com.tistory.jeongs0222.namdaein.ui.activity.editnickname.EditNicknameActivity
import com.tistory.jeongs0222.namdaein.ui.activity.inquire.InquireActivity
import com.tistory.jeongs0222.namdaein.ui.activity.written.WrittenActivity
import com.tistory.jeongs0222.namdaein.utils.CustomToast
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
        more_edit_textView.setOnClickListener {
            startActivity(EditNicknameActivity::class.java)
        }

        more_written_textView.setOnClickListener {
            startActivity(WrittenActivity::class.java)
        }

        more_message_textView.setOnClickListener {

            val toastMessage = CustomToast(activity as Activity)
            toastMessage.makeText("준비중입니다.", Toast.LENGTH_SHORT).show()

            //startActivity(ChatListActivity::class.java)
        }

        more_inquire_textView.setOnClickListener {
            startActivity(InquireActivity::class.java)
        }
    }

    private fun startActivity(activityClass: Class<*>) {
        val intent = Intent(activity, activityClass)

        startActivity(intent)
    }

}
