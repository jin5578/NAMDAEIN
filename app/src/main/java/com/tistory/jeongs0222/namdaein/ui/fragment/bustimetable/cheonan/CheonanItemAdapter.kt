package com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.cheonan

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.capital.CapitalItemAdapter
import org.w3c.dom.Text


class CheonanItemAdapter(internal val context: Context): RecyclerView.Adapter<CheonanItemAdapter.ViewHolder>() {

    var item: MutableList<Model.busTimeTable> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cheonan, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val items = item[position]

        holder.cheonan_location_textView.text = items.boardingGate
        holder.cheonan_school_textView.text = items.school

        when(position) {
            0 -> holder.null2.visibility = View.VISIBLE

            6 -> holder.null1.visibility = View.GONE

            7 -> {
                holder.null2.text = "2코스"
                holder.null2.visibility = View.VISIBLE
            }

            13 -> holder.null1.visibility = View.GONE
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
        val cheonan_location_textView: TextView
        val cheonan_school_textView: TextView
        val null1: ImageView
        val null2: TextView

        init {
            cheonan_location_textView = itemView.findViewById(R.id.cheonan_location_textView)
            cheonan_school_textView = itemView.findViewById(R.id.cheonan_school_textView)
            null1 = itemView.findViewById(R.id.null1)
            null2 = itemView.findViewById(R.id.null2)
        }
    }
}