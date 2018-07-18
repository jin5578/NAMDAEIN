package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.content.Context


interface MarketWriteContract {

    interface View {
        fun spinner(): com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpSpinnerFunc()
    }
}