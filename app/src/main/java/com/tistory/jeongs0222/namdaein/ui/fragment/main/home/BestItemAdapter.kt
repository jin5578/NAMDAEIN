package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.boarddetail.BoardDetailActivity


class BestItemAdapter(internal val context: Context): RecyclerView.Adapter<BestItemAdapter.ViewHolder>() {

    var item: MutableList<Model.boardItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_best_board, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        holder.best_nickname_textView.text = items.nickname
        holder.best_date_textView.text = items.date
        holder.best_title_textView.text = items.title
        holder.best_content_textView.text = items.content
        holder.best_thumbUp_textView.text = items.nice

        if(items.image0.isNotEmpty()) {
            holder.best_validate_imageView.visibility = View.VISIBLE
        }

        holder.best_entire_constraintLayout.setOnClickListener {
            val order = items.order

            val intent = Intent(it.context, BoardDetailActivity::class.java)
            intent.putExtra("order", order)

            it.context.startActivity(intent)
        }
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


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val best_entire_constraintLayout: ConstraintLayout
        val best_nickname_textView: TextView
        val best_date_textView: TextView
        val best_title_textView: TextView
        val best_content_textView: TextView
        val best_thumbUp_textView: TextView
        val best_validate_imageView: ImageView

        init {
            best_entire_constraintLayout = itemView.findViewById(R.id.best_entire_constraintLayout)
            best_nickname_textView = itemView.findViewById(R.id.best_nickname_textView)
            best_date_textView = itemView.findViewById(R.id.best_date_textView)
            best_title_textView = itemView.findViewById(R.id.best_title_textView)
            best_content_textView = itemView.findViewById(R.id.best_content_textView)
            best_thumbUp_textView = itemView.findViewById(R.id.best_thumbUp_textView)
            best_validate_imageView = itemView.findViewById(R.id.best_validate_imageView)
        }
    }
}