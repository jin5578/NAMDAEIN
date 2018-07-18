package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.marketwrite.MarketWriteActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.thanel.swipeactionview.SwipeActionView
import me.thanel.swipeactionview.SwipeDirection
import me.thanel.swipeactionview.SwipeGestureListener


class WrittenMarketAdapter(internal var context: Context): RecyclerView.Adapter<WrittenMarketAdapter.ViewHolder>() {

    var item: MutableList<Model.writtenMarketItem> = ArrayList()

    private var disposable: Disposable? = null

    private val apiClient by lazy { ApiClient.create() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_written_market, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val items = item[position]

        val order = items.order

        holder.written_market_category.text = classify_category(items.category)     //일단은 Int값으로 넘어온거
        holder.written_market_date.text = items.date
        holder.written_market_title.text = items.title

        holder.written_market_layout.apply {
            setRippleColor(SwipeDirection.Left, Color.GRAY)
            setRippleColor(SwipeDirection.Right, Color.GRAY)

            swipeGestureListener = object : SwipeGestureListener {
                override fun onSwipedLeft(swipeActionView: SwipeActionView): Boolean {
                    customAlertDialog(holder, position, swipeActionView, "삭제하시겠습니까?", 0, order)

                    return true
                }

                override fun onSwipedRight(swipeActionView: SwipeActionView): Boolean {
                    customAlertDialog(holder, position, swipeActionView, "수정하시겠습니까?", 1, order)

                    return true
                }

            }
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
            0 -> return "[여성의류]"

            1 -> return "[남성의류]"

            2 -> return "[패션잡화]"

            3 -> return "[뷰티]"

            4 -> return "[도서]"

            5 -> return "[티켓]"

            6 -> return "[가전제품]"

            7 -> return "[생활]"

            8 -> return "[원룸]"

            9 -> return "[기]"

            else -> return null!!
        }
    }

    private fun customAlertDialog(holder: ViewHolder, position: Int, swipeActionView: SwipeActionView, message: String, sort: Int, order: Int) {
        val builder = AlertDialog.Builder(holder.written_market_layout.context)
        builder.apply {
            setTitle("알림")
            setMessage(message)
            setCancelable(false)
            setPositiveButton("확인", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if(sort == 0) {     //삭제 기능
                        disposable = apiClient.deleteWrittenMarket(order!!)
                                .subscribeOn(Schedulers.io())
                                .doOnNext {
                                    if(it.value == 0) {
                                        item.removeAt(position)
                                    }
                                }
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete {
                                    notifyChanged()
                                }
                                .doOnError { it.printStackTrace() }
                                .subscribe()
                    } else {
                        val intent = Intent(swipeActionView.context, MarketWriteActivity::class.java)

                        intent.putExtra("sort", 1)
                        intent.putExtra("order", order)

                        swipeActionView.context.startActivity(intent)

                    }
                }
            })

            setNegativeButton("취소", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0!!.cancel()
                }

            })
        }

        val alertDialog = builder.create()

        alertDialog.show()
    }

    fun addAllItems(e: MutableList<Model.writtenMarketItem>) = item.addAll(e)

    fun clearAllItems() = item.clear()

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