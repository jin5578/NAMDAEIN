package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenboard

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
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.boarddetail.BoardDetailActivity
import com.tistory.jeongs0222.namdaein.ui.activity.boardwrite.BoardWriteActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.thanel.swipeactionview.SwipeActionView
import me.thanel.swipeactionview.SwipeDirection
import me.thanel.swipeactionview.SwipeGestureListener


class WrittenBoardAdapter(internal var context: Context): RecyclerView.Adapter<WrittenBoardAdapter.ViewHolder>() {

    private var item: MutableList<Model.writtenBoardItem> = ArrayList()

    private var disposable: Disposable? = null

    private val apiClient by lazy { ApiClient.create() }


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

        holder.written_board_layout.setOnClickListener {
            val intent = Intent(it.context, BoardDetailActivity::class.java)

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

    private fun classify_category(category: Int): String {
        return when(category) {
            0 -> "자유"

            1 -> "분실물"

            2 -> "홍보"

            3 -> "동아리"

            else -> null!!
        }
    }

    private fun customAlertDialog(holder: ViewHolder, position: Int, swipeActionView: SwipeActionView, message: String, sort: Int, order: Int) {
        val builder = AlertDialog.Builder(holder.written_board_layout.context)
        builder.apply {
            setTitle("알림")
            setMessage(message)
            setCancelable(false)
            setPositiveButton("확인", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if(sort == 0) {
                        disposable = apiClient.deleteWrittenBoard(order!!)
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
                        val intent = Intent(swipeActionView.context, BoardWriteActivity::class.java)

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

        val alerDialog = builder.create()

        alerDialog.show()
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