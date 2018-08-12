package com.tistory.jeongs0222.namdaein.ui.activity.notice

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class NoticeItemAdapter(internal val context: Context): RecyclerView.Adapter<NoticeItemAdapter.ViewHolder>() {

    var item: MutableList<Model.noticeInformation> = ArrayList()

    private var open: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        holder.notice_title_textView.text = items.noticeTitle
        holder.notice_content_textView.text = items.noticeContent

        holder.notice_entire_constraint.setOnClickListener {

            if(open == true) {
                open = false

                holder.notice_content_textView.visibility = View.GONE

                holder.notice_visible_imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_expand_less_black_24dp))
            } else {
                open = true

                holder.notice_content_textView.visibility = View.VISIBLE

                holder.notice_visible_imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_expand_more_black_24dp))
            }
        }

    }

    override fun getItemCount(): Int {
        return if (item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.noticeInformation>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notice_entire_constraint: LinearLayout
        val notice_visible_imageView: ImageView
        val notice_title_textView: TextView
        val notice_content_textView: TextView

        init {
            notice_entire_constraint = itemView.findViewById(R.id.notice_entire_constraint)
            notice_visible_imageView = itemView.findViewById(R.id.notice_visible_imageView)
            notice_title_textView = itemView.findViewById(R.id.notice_title_textView)
            notice_content_textView = itemView.findViewById(R.id.notice_content_textView)
        }
    }
}