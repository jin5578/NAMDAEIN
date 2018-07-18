package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.tistory.jeongs0222.namdaein.model.Model


interface MarketDetailContract {

    interface View {
        fun progressBar(value: Int)

        fun imageViewPagerVisible(value: Int)

        fun recyclerView(): RecyclerView

        fun sendEditText(): EditText

        fun sendVisible(value: Int)

        fun sendClickable(value: Int)

        fun toastMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (String, Model.marketItem) -> Unit)

        fun setUpRecyclerView()

        fun setUpCommentData()

        fun setUpCommentFunc()

        fun setUpSendFunc()

        fun disposableClear()
    }
}