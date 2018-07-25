package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.content.Context
import android.support.v4.app.FragmentManager
import android.widget.EditText
import android.widget.LinearLayout
import com.tistory.jeongs0222.namdaein.model.Model


interface MarketWriteContract {

    interface View {
        fun spinner(): com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

        fun selectedLinear(): LinearLayout

        fun confirmClickable(value: Int)

        fun progressBar(value: Int)

        fun title(): EditText

        fun content(): EditText

        fun price(): EditText

        fun viewFinish()

        fun toastMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpSpinnerFunc()

        fun setUpMultiShow(supportFragmentManager: FragmentManager)

        fun setUpBringMarket(order: Int, callback: (String, Model.marketItem) -> Unit)

        fun setUpConfirmFunc()

        fun setUpEditConfirmFunc(order: Int)

        fun disposableClear()
    }
}