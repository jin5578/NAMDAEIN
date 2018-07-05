package com.tistory.jeongs0222.namdaein.ui.fragment.board

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R


//추후에 ViewHolder 뜯어내기


class BoardItemAdapter(internal var item: MutableList<Object>, internal var context: Context): RecyclerView.Adapter<BoardItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        if(item == null) {
            return 0
        } else {
            return item.size

        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}