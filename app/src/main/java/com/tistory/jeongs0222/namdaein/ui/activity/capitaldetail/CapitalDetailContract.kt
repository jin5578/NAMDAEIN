package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import com.tistory.jeongs0222.namdaein.model.Model


interface CapitalDetailContract {

    interface View {
        fun imageViewPager(): ViewPager

        fun dotsLinearLayout(): LinearLayout
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpInitData(order: Int, callback: (Model.busTimeTable) -> Unit)

        fun pictureViewPager(images: MutableList<String>)

        fun addDots()
    }
}