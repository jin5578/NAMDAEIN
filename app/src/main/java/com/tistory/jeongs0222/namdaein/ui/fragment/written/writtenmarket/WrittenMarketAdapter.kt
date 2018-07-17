package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class WrittenMarketAdapter(internal var context: Context): RecyclerView.Adapter<WrittenMarketAdapter.ViewHolder>() {

    var item: MutableList<Model.writtenMarketItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_written_market, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        holder.written_market_category.text = items.category.toString()     //일단은 Int값으로 넘어온거
        holder.written_market_date.text = items.date
        holder.written_market_title.text = items.title
    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.writtenMarketItem>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val written_market_layout: me.thanel.swipeactionview.SwipeActionView
        val written_market_constraintLayout: ConstraintLayout
        val written_market_category: TextView
        val written_market_date: TextView
        val written_market_title: TextView

        init {
            written_market_layout = itemView.findViewById(R.id.written_market_layout)
            written_market_constraintLayout = itemView.findViewById(R.id.written_market_constraintLayout)
            written_market_category = itemView.findViewById(R.id.written_market_category)
            written_market_date = itemView.findViewById(R.id.written_market_date)
            written_market_title = itemView.findViewById(R.id.written_market_title)
        }
    }
}