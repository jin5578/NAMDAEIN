package com.tistory.jeongs0222.namdaein.ui.fragment.market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.marketdetail.MarketDetailActivity


class MarketItemAdapter(internal val context: Context): RecyclerView.Adapter<MarketItemAdapter.ViewHolder>() {

    var item: MutableList<Model.marketItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_market, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        Glide.with(context)
                .asBitmap()
                .load(items.image0)
                .apply(RequestOptions()
                        .encodeFormat(Bitmap.CompressFormat.PNG)
                        .placeholder(R.drawable.ic_refresh_black_24dp)
                        .error(R.drawable.ic_refresh_black_24dp)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .into(holder.market_imageView)

        holder.market_title_textView.text = items.title
        holder.market_price_textView.text = items.price

        holder.market_entire_layout.setOnClickListener {
            val order = items.order

            val intent = Intent(it.context, MarketDetailActivity::class.java)
            intent.putExtra("order", order)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if(item.isEmpty()) {
            0
        } else {
            item.size
        }
    }

    fun addAllItems(e: MutableList<Model.marketItem>) = item.addAll(e)

    fun notifyChanged() = notifyDataSetChanged()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val market_entire_layout: ConstraintLayout
        val market_imageView: ImageView
        val market_title_textView: TextView
        val market_price_textView: TextView

        init {
            market_entire_layout = itemView.findViewById(R.id.write_entire_layout)
            market_imageView = itemView.findViewById(R.id.write_imageView)
            market_title_textView = itemView.findViewById(R.id.market_title_textView)
            market_price_textView = itemView.findViewById(R.id.market_price_textView)
        }
    }
}