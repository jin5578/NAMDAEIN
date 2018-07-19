package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.content.Context
import com.tistory.jeongs0222.namdaein.model.Model


interface BoardWriteContract {

    interface View {
        fun spinner(): com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

        fun viewFinish()
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpSpinnerFunc()

        fun setUpBringBoard(order: Int, callback: (String, Model.boardItem) -> Unit)

        fun disposableClear()
    }
}