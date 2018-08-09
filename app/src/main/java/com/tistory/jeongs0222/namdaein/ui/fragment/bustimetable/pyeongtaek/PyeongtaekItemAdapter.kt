package com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.pyeongtaek

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class PyeongtaekItemAdapter(internal val context: Context): RecyclerView.Adapter<PyeongtaekItemAdapter.ViewHolder>() {

    var item: MutableList<Model.busTimeTable> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pyeongtaek, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val items = item[position]

        holder.pyeongtaek_location_textView.text = items.boardingGate
        holder.pyeongtaek_school_textView.text = items.school

        if(position == 6) {
            holder.null1.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return if (item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.busTimeTable>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pyeongtaek_location_textView: TextView
        val pyeongtaek_school_textView: TextView
        val null1: ImageView

        init {
            pyeongtaek_location_textView = itemView.findViewById(R.id.pyeongtaek_location_textView)
            pyeongtaek_school_textView = itemView.findViewById(R.id.pyeongtaek_school_textView)
            null1 = itemView.findViewById(R.id.null1)
        }
    }
}