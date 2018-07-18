package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity(), ChatRoomContract.View {

    private lateinit var mPresenter: ChatRoomPresenter

    private lateinit var chatInfo: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        init()
    }

    private fun init() {
        mPresenter = ChatRoomPresenter()

        mPresenter.setView(this, this)

        getValue()

        mPresenter.checkRoom(chatInfo)

        mPresenter.setUpMessageFunc()

        onClickEvent()
    }

    private fun getValue() {
        val intent = intent

        chatInfo = intent.getStringArrayListExtra("intentExtra")
    }

    private fun onClickEvent() {
        chat_send_textView.setOnClickListener {
            sendClickable(1)

            mPresenter.setUpSendFunc()
        }
    }

    override fun recyclerView(): RecyclerView {
        return chat_recyclerView
    }

    override fun sendEditText(): EditText {
        return chat_send_editText
    }

    override fun sendVisible(value: Int) {
        when(value) {
            0 -> chat_send_textView.visibility = View.VISIBLE

            1 -> chat_send_textView.visibility = View.GONE
        }
    }

    override fun sendClickable(value: Int) {
        when(value) {
            0 -> chat_send_textView.isClickable = true

            1 -> chat_send_textView.isClickable = false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
