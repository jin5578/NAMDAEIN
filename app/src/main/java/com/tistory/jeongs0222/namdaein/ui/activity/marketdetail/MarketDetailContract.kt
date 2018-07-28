package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.LinearLayout
import com.tistory.jeongs0222.namdaein.model.Model


interface MarketDetailContract {

    interface View {
        fun progressBar(value: Int)

        fun imageViewPager(): ViewPager

        fun dotsLinearLayout(): LinearLayout

        fun imageViewPagerVisible(value: Int)

        fun recyclerView(): RecyclerView

        fun sendEditText(): EditText

        fun sendVisible(value: Int)

        fun sendClickable(value: Int)

        fun toastMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (Model.marketItem) -> Unit)

        fun pictureViewPager(images: MutableList<String>)

        fun addDots()

        fun setUpRecyclerView()

        fun setUpCommentData()

        fun setUpCommentFunc()

        fun setUpSendFunc()

        fun disposableClear()
    }
}