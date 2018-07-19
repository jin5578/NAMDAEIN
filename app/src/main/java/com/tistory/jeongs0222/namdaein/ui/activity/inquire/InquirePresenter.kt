package com.tistory.jeongs0222.namdaein.ui.activity.inquire

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class InquirePresenter: InquireContract.Presenter, TextWatcher {

    private lateinit var view: InquireContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private lateinit var dbHelper: DBHelper

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: InquireContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpEditFunc() {
        view.content().addTextChangedListener(this@InquirePresenter)
    }

    override fun setUpConfirmFunc() {
        if(view.content().length() != 0) {
            disposable = apiClient.inquire(dbHelper.getGoogle_uId()!!, view.content().text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        view.viewFinish()
                    }
                    .doOnError { it.printStackTrace() }
                    .subscribe()
        } else {
            view.toastMessage("빈 칸을 작성할 수 없습니다.")
        }
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        view.count(p0!!.length)
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }


}