package com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.capital

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail.CapitalDetailActivity


class CapitalItemAdapter(internal val context: Context): RecyclerView.Adapter<CapitalItemAdapter.ViewHolder>() {

    var item: MutableList<Model.busTimeTable> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_capital, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val items = item[position]

        holder.capital_location_textView.text = items.location
        holder.capital_school_textView.text = items.school
        holder.capital_home_textView.text = items.home
        holder.capital_price_textView.text = items.price.toString()+"원"

        holder.capital_entire_layout.setOnClickListener {
            val intent = Intent(it.context, CapitalDetailActivity::class.java)
            intent.putExtra("order", items.order)

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

    fun addAllItems(e: MutableList<Model.busTimeTable>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val capital_entire_layout: LinearLayout
        val capital_location_textView: TextView
        val capital_school_textView: TextView
        val capital_home_textView: TextView
        val capital_price_textView: TextView


        init {
            capital_entire_layout = itemView.findViewById(R.id.capital_entire_layout)
            capital_location_textView = itemView.findViewById(R.id.capital_location_textView)
            capital_school_textView = itemView.findViewById(R.id.capital_school_textView)
            capital_home_textView = itemView.findViewById(R.id.capital_home_textView)
            capital_price_textView = itemView.findViewById(R.id.capital_price_textView)
        }
    }
}