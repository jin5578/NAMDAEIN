package com.tistory.jeongs0222.namdaein.ui.activity.termsofuse

import android.content.Context


class TermsOfUsePresenter: TermsOfUseContract.Presenter {

    private lateinit var view: TermsOfUseContract.View
    private lateinit var context: Context

    override fun setView(view: TermsOfUseContract.View, context: Context) {
        this.view = view
        this.context = context
    }

}