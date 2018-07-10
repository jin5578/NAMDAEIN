package com.tistory.jeongs0222.namdaein.ui.fragment.board

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


//추후에 ViewHolder 뜯어내야함
class BoardItemAdapter(internal var context: Context) : RecyclerView.Adapter<BoardItemAdapter.ViewHolder>() {

    var item: MutableList<Model.boardItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val items = item[position]

        holder.board_title_textView.text = items.title
        holder.board_date_textView.text = items.date
        holder.board_content_textView.text = items.content
        holder.board_nickname_textView.text = items.nickname
        holder.board_thumbUp_textView.text = items.nice
        holder.board_thumbDown_textView.text = items.dislike

    }

    override fun getItemCount(): Int {
        return if (item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.boardItem>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()

    fun addItems(e: Model.boardItem) = item.add(e)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val board_entire_layout: ConstraintLayout
        val board_title_textView: TextView
        val board_date_textView: TextView
        val board_content_textView: TextView
        val board_nickname_textView: TextView
        val board_thumbUp_textView: TextView
        val board_thumbDown_textView: TextView

        init {
            board_entire_layout = itemView.findViewById(R.id.board_entire_layout)
            board_title_textView = itemView.findViewById(R.id.board_title_textView)
            board_date_textView = itemView.findViewById(R.id.board_date_textView)
            board_content_textView = itemView.findViewById(R.id.board_content_textView)
            board_nickname_textView = itemView.findViewById(R.id.board_nickname_textView)
            board_thumbUp_textView = itemView.findViewById(R.id.board_thumbUp_textView)
            board_thumbDown_textView = itemView.findViewById(R.id.board_thumbDown_textView)
        }
    }
}

