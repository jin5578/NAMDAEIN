package com.tistory.jeongs0222.namdaein.ui.activity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class CommentAdapter(internal val context: Context): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var item: MutableList<Model.boardCommentItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        holder.comment_nickname_textView.text = items.nickname
        holder.comment_content_textView.text = items.content
        holder.comment_date_textView.text = items.date
    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.boardCommentItem>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val comment_nickname_textView: TextView
        val comment_content_textView: TextView
        val comment_date_textView: TextView

        init {
            comment_nickname_textView = itemView.findViewById(R.id.comment_nickname_textView)
            comment_content_textView = itemView.findViewById(R.id.comment_content_textView)
            comment_date_textView = itemView.findViewById(R.id.comment_date_textView)
        }
    }
}