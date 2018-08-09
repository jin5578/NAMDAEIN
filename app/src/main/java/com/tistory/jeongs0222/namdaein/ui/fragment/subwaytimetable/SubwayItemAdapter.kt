package com.tistory.jeongs0222.namdaein.ui.fragment.subwaytimetable

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class SubwayItemAdapter(internal val context: Context): RecyclerView.Adapter<SubwayItemAdapter.ViewHolder>() {

    var item: MutableList<Model.subwayTimeTable> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subway, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        holder.subway_time_textView.text = items.time
        holder.subway_destination_textView.text = items.destination
    }

    override fun getItemCount(): Int {
        return if (item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.subwayTimeTable>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val subway_time_textView: TextView
        val subway_destination_textView: TextView

        init {
            subway_time_textView = itemView.findViewById(R.id.subway_time_textView)
            subway_destination_textView = itemView.findViewById(R.id.subway_destination_textView)
        }
    }
}