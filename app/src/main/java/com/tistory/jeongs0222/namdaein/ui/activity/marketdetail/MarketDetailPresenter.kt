package com.tistory.jeongs0222.namdaein.ui.activity.marketdetail

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.CommentAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class MarketDetailPresenter: MarketDetailContract.Presenter, TextWatcher {
    private lateinit var view: MarketDetailContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var order: Int = 0

    private var currentDate: String = ""

    private lateinit var mAdapter: CommentAdapter

    private val apiClient by lazy { ApiClient.create() }

    override fun setView(view: MarketDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (String, Model.marketItem) -> Unit) {
        this.order = order

        view.progressBar(0)

        disposable = apiClient.bringMarketDetail(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {  }
                .doOnError {
                    it.printStackTrace()
                    view.progressBar(1)
                }
                .subscribe ({
                    if(it.image0.isEmpty()) {
                        view.imageViewPagerVisible(1)
                    } else {
                        view.imageViewPagerVisible(0)
                    }

                    callback("complete", it)
                })

    }

    override fun setUpRecyclerView() {
        mAdapter = CommentAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpCommentData() {
        Log.e("order", order.toString())
        disposable = apiClient.bringMarketComment(order)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    if(it.comment.isNotEmpty()) {
                        mAdapter.addAllItems(it.comment)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    mAdapter.notifyChanged()
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()
    }

    override fun setUpCommentFunc() {
        view.sendEditText().addTextChangedListener(this@MarketDetailPresenter)
    }

    override fun setUpSendFunc() {
        bringDate()

        if(view.sendEditText().text.isNotEmpty()) {
            disposable = apiClient.writingMarketComment(order, "jHtFtSfO2lMG3NLADGojZ1oG9Da2", view.sendEditText().text.toString(), currentDate)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        setUpCommentData()
                    }
                    .doOnError {
                        it.printStackTrace()
                    }
                    .subscribe {
                        if(it.value == 1) {
                            view.snackBar(it.message)
                        }

                        view.sendEditText().text = null
                        view.sendClickable(0)
                    }
        }
    }

    override fun disposableClear() = disposable!!.dispose()

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(p0.toString().length == 0) {
            view.sendVisible(1)
        } else {
            view.sendVisible(0)
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    private fun bringDate() {
        val now = System.currentTimeMillis()

        val date = Date(now)

        val sdf = SimpleDateFormat("yy.MM.dd HH:mm")

        currentDate = sdf.format(date)
    }
}