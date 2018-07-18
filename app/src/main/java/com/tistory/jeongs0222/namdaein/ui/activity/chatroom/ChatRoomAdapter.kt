package com.tistory.jeongs0222.namdaein.ui.activity.chatroom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.model.firebase.Chat
import kotlinx.android.synthetic.main.item_chatting.view.*


class ChatRoomAdapter(val context: Context, val chatRoomId: String, val chatInfo: ArrayList<String>): RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {

    private var item: MutableList<Chat.Comment>

    private var dbHelper: DBHelper

    private var google_uId: String
    private var writtenUserGoogle_uId: String
    private var writtenNickname: String

    init {
        item = ArrayList()

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)

        google_uId = dbHelper.getGoogle_uId()!!
        writtenUserGoogle_uId = chatInfo.get(0)
        writtenNickname = chatInfo.get(1)

        chattingContent()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chatting, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(item.get(position).userId.equals(google_uId)) {
            holder.itemView.chatting_opponent_linearLayout.visibility = View.GONE
            holder.itemView.chatting_my_linearLayout.visibility = View.VISIBLE

            holder.itemView.chatting_myContent_textView.text = item.get(position).message
        } else {
            holder.itemView.chatting_opponent_linearLayout.visibility = View.VISIBLE
            holder.itemView.chatting_my_linearLayout.visibility = View.GONE

            holder.itemView.chatting_opponentNickname_textView.text = writtenNickname
            holder.itemView.chatting_opponentContent_textView.text = item.get(position).message
        }
    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val chatting_opponent_linearLayout: LinearLayout
        val chatting_opponentNickname_textView: TextView
        val chatting_opponentContent_textView: TextView
        val chatting_opponentDate_textView: TextView
        val chatting_my_linearLayout: LinearLayout
        val chatting_myDate_textView: TextView
        val chatting_myContent_textView: TextView

        init {
            chatting_opponent_linearLayout = itemView.findViewById(R.id.chatting_opponent_linearLayout)
            chatting_opponentNickname_textView = itemView.findViewById(R.id.chatting_opponentNickname_textView)
            chatting_opponentContent_textView = itemView.findViewById(R.id.chatting_opponentContent_textView)
            chatting_opponentDate_textView = itemView.findViewById(R.id.chatting_opponentDate_textView)
            chatting_my_linearLayout = itemView.findViewById(R.id.chatting_my_linearLayout)
            chatting_myDate_textView = itemView.findViewById(R.id.chatting_myDate_textView)
            chatting_myContent_textView = itemView.findViewById(R.id.chatting_myContent_textView)
        }
    }

    private fun chattingContent() {
        FirebaseDatabase.getInstance().reference.child("chatrooms").child(chatRoomId).child("comments").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                item.clear()

                for(snapshot: DataSnapshot in p0!!.children) {
                    item.add(snapshot.getValue(Chat.Comment::class.java)!!)
                }

                notifyDataSetChanged()
            }

        })
    }
}