package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.content.Context
import com.tistory.jeongs0222.namdaein.model.Model


interface CapitalDetailContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (Model.busTimeTable) -> Unit)
    }
}