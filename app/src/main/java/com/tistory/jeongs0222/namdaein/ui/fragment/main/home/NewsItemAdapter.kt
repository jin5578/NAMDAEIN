package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model


class NewsItemAdapter(internal val context: Context) : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {

    var item: MutableList<Model.campusNewsItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_campusnews, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = item[position]

        holder.campusNews_title.text = items.title

        Glide.with(context)
                .load(items.image)
                .thumbnail(0.5f)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_refresh_black_24dp)
                        .error(R.drawable.ic_refresh_black_24dp)
                )
                .into(object : ViewTarget<ConstraintLayout, Drawable>(holder.campusNews_constraintLayout) {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        this.view.background = resource
                    }

                })

        holder.campusNews_constraintLayout.setOnClickListener {
            if(items.address.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(items.address))

                it.context.startActivity(intent)
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

    fun addAllItems(e: MutableList<Model.campusNewsItem>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val campusNews_constraintLayout: ConstraintLayout
        val campusNews_imageView: ImageView
        val campusNews_title: TextView

        init {
            campusNews_constraintLayout = itemView.findViewById(R.id.campusNews_constraintLayout)
            campusNews_imageView = itemView.findViewById(R.id.campusNews_imageView)
            campusNews_title = itemView.findViewById(R.id.campusNews_title)
        }
    }
}