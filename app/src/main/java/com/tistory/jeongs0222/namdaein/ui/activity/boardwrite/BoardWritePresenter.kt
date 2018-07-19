package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardWritePresenter: BoardWriteContract.Presenter {

    private lateinit var view: BoardWriteContract.View
    private lateinit var context: Context

    private val spinnerList = arrayOf("자유", "분실물", "홍보", "동아리")

    private var disposable: Disposable? = null

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: BoardWriteContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpSpinnerFunc() {
        val arrayAdapter = ArrayAdapter<String>(context, R.layout.simple_dropdown_item_1line, spinnerList)

        view.spinner().setAdapter(arrayAdapter)
    }

    override fun setUpBringBoard(order: Int, callback: (String, Model.boardItem) -> Unit) {
        disposable = apiClient.beforeBoardData(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe {
                    callback("complete", it)
                    view.spinner().setText(spinnerList.get(it.category))
                }
    }

    override fun disposableClear() = disposable!!.dispose()
}