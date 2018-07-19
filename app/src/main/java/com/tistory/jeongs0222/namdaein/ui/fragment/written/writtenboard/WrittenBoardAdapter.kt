package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenboard

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model
import me.thanel.swipeactionview.SwipeDirection


class WrittenBoardAdapter(internal var context: Context): RecyclerView.Adapter<WrittenBoardAdapter.ViewHolder>() {

    private var item: MutableList<Model.writtenBoardItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_written, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        val order = items.order

        holder.written_board_title.visibility = View.VISIBLE

        holder.written_board_category.text = classify_category(items.category)
        holder.written_board_title.text = items.title
        holder.written_board_date.text = items.date
        holder.written_board_content.text = items.content

        holder.written_board_layout.apply {
            setRippleColor(SwipeDirection.Left, Color.GRAY)
            setRippleColor(SwipeDirection.Right, Color.GRAY)

        }

    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    private fun classify_category(category: Int): String {
        when(category) {
            0 -> return "자유"

            1 -> return "분실물"

            2 -> return "홍보"

            3 -> return "동아리"

            else -> return null!!
        }
    }

    fun addAllItems(e: MutableList<Model.writtenBoardItem>) = item.addAll(e)

    fun clearAllItems() = item.clear()

    fun notifyChanged() = notifyDataSetChanged()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val written_board_layout: me.thanel.swipeactionview.SwipeActionView
        val written_board_constraintLayout: ConstraintLayout
        val written_board_category: TextView
        val written_board_title: TextView
        val written_board_date: TextView
        val written_board_content: TextView

        init {
            written_board_layout = itemView.findViewById(R.id.written_layout)
            written_board_constraintLayout = itemView.findViewById(R.id.written_constraintLayout)
            written_board_category = itemView.findViewById(R.id.written_category)
            written_board_title = itemView.findViewById(R.id.written_title)
            written_board_date = itemView.findViewById(R.id.written_date)
            written_board_content = itemView.findViewById(R.id.written_content)
        }
    }
}