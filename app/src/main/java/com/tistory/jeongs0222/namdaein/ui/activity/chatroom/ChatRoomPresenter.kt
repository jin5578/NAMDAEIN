package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.Editable
import android.text.TextWatcher
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tistory.jeongs0222.namdaein.model.firebase.Chat
import com.tistory.jeongs0222.namdaein.model.DBHelper


class ChatRoomPresenter: ChatRoomContract.Presenter, TextWatcher {

    private lateinit var view: ChatRoomContract.View
    private lateinit var context: Context

    private lateinit var dbHelper: DBHelper

    private lateinit var google_uId: String
    private lateinit var writtenUserGoogle_uId: String

    private lateinit var chatInfo: ArrayList<String>

    private lateinit var chatRoomId: String

    private lateinit var mAdapter: ChatRoomAdapter

    override fun setView(view: ChatRoomContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpMessageFunc() {
        view.sendEditText().addTextChangedListener(this@ChatRoomPresenter)
    }

    override fun checkRoom(chatInfo: ArrayList<String>) {
        google_uId = dbHelper.getGoogle_uId()!!

        this.chatInfo = chatInfo

        this.writtenUserGoogle_uId = chatInfo.get(0)


        FirebaseDatabase.getInstance()
                .getReference()
                .child("chatrooms")
                .orderByChild("users/" + google_uId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot?) {
                        if(p0!!.hasChildren().not()) {

                        }

                        for(snapshot: DataSnapshot in p0.children) {
                            val findChat: Chat = snapshot.getValue(Chat::class.java)!!

                            if(findChat.users.containsKey(writtenUserGoogle_uId)) {
                                chatRoomId = snapshot.key

                                setRecyclerView()

                                return
                            }
                        }

                        val newChat = Chat()
                        (newChat.users as HashMap<String, Boolean>).put(google_uId, false)
                        (newChat.users as HashMap<String, Boolean>).put(writtenUserGoogle_uId, false)

                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("chatrooms")
                                .push()
                                .setValue(newChat)
                                .addOnCompleteListener { task ->
                                    if (task.isComplete) {
                                        FirebaseDatabase.getInstance()
                                                .reference
                                                .child("chatrooms")
                                                .orderByChild("users/" + google_uId)
                                                .equalTo(true)
                                                .addListenerForSingleValueEvent(object : ValueEventListener {

                                                    override fun onDataChange(p0: DataSnapshot?) {
                                                        for (snapShot: DataSnapshot in p0!!.children) {
                                                            val newFindChat: Chat = snapShot.getValue(Chat::class.java)!!

                                                            if (newFindChat.users.containsKey(writtenUserGoogle_uId)) {
                                                                chatRoomId = snapShot.key

                                                                setRecyclerView()
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(p0: DatabaseError?) {

                                                    }
                                                })
                                    }
                                }

                    }


                    override fun onCancelled(p0: DatabaseError?) {

                    }
                })
    }

    private fun setRecyclerView() {
        mAdapter = ChatRoomAdapter(context, chatRoomId, chatInfo)

        view.recyclerView().apply {
            adapter = mAdapter

            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpSendFunc() {
        val comment: Chat.Comment = Chat.Comment()

        comment.userId = google_uId
        comment.message = view.sendEditText().text.toString()

        FirebaseDatabase.getInstance()
                .reference
                .child("chatrooms")
                .child(chatRoomId)
                .child("comments")
                .push()
                .setValue(comment)
                .addOnCompleteListener({ task ->
                    if(task.isSuccessful) {
                        view.sendEditText().text = null

                        view.sendClickable(0)
                    }
                })
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(p0.toString().length == 0) {
            view.sendVisible(1)
        } else {
            view.sendVisible(0)
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }



}