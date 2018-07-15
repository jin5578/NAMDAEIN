package com.tistory.jeongs0222.namdaein.ui.activity.termsofuse

import android.content.Context


interface TermsOfUseContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View, context: Context)
    }
}