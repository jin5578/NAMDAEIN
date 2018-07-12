package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context
import com.tistory.jeongs0222.namdaein.api.ApiService
import com.tistory.jeongs0222.namdaein.model.Model


interface BoardDetailContract {

    interface View {
        fun progressBar(value: Int)

        fun imageViewPagerVisible(value: Int)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (String, Model.boardItem) -> Unit)
    }
}