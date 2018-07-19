package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.content.Context
import android.widget.EditText
import com.tistory.jeongs0222.namdaein.model.Model


interface BoardWriteContract {

    interface View {
        fun spinner(): com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

        fun confirmClickable(value: Int)

        fun progressBar(value: Int)

        fun title(): EditText

        fun content(): EditText

        fun viewFinish()

        fun toastMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpSpinnerFunc()

        fun setUpBringBoard(order: Int, callback: (String, Model.boardItem) -> Unit)

        fun setUpConfirmFunc()

        fun setUpEditConfirmFunc(order: Int)

        fun disposableClear()
    }
}