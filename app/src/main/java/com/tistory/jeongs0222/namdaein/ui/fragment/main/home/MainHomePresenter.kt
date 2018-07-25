package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainHomePresenter: MainHomeContract.Presenter {

    private lateinit var view: MainHomeContract.View
    private lateinit var context: Context


    override fun setView(view: MainHomeContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}