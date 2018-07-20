package com.tistory.jeongs0222.namdaein.ui.activity.chatlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.model.firebase.Chat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ChatListAdapter(val context: Context): RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    private var item: MutableList<Chat>

    private var dbHelper: DBHelper

    private var google_uId: String

    private var disposable: Disposable? = null

    private val apiClient by lazy { ApiClient.create() }


    init {
        item = ArrayList()

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)

        google_uId = dbHelper.getGoogle_uId()!!

        Log.e("google_uId", google_uId)

        FirebaseDatabase.getInstance()
                .getReference()
                .child("chatrooms")
                .orderByChild("users/" + google_uId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot?) {
                        item.clear()

                        if(p0!!.exists()) {
                            Log.e("exist", "exist")
                            for(snapshot: DataSnapshot in p0!!.children) {
                                item.add(snapshot.getValue(Chat::class.java)!!)
                            }
                        } else {
                            Log.e("NonExist", "NonExist")
                        }



                        notifyDataSetChanged()
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }
                })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chatlist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        for(user: String in item.get(position).users.keys) {
            if(!user.equals(google_uId)) {
                getOpponentInfo(holder, user)
            }
        }

    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    private fun getOpponentInfo(holder: ViewHolder, opponent_uId: String) {
        disposable = apiClient.userInfo(opponent_uId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    holder.chatlist_nickname_textView.text = it.nickname
                }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val chatlist_imageView: ImageView
        val chatlist_nickname_textView: TextView
        val chatlist_content_textView: TextView

        init {
            chatlist_imageView = itemView.findViewById(R.id.chatlist_imageView)
            chatlist_nickname_textView = itemView.findViewById(R.id.chatlist_nickname_textView)
            chatlist_content_textView = itemView.findViewById(R.id.chatlist_content_textView)
        }
    }
}