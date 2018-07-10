package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardFreePresenter : BoardFreeContract.Presenter, RecyclerView.OnScrollListener() {

    private lateinit var view: BoardFreeContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var pageNumber: Int = 0

    private var isFirstLoad = true

    private var isLoading = false

    private lateinit var mAdapter: BoardItemAdapter

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: BoardFreeContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        mAdapter = BoardItemAdapter(context)

        view.recyclerView().apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData() {
        view.progressBar(0)
        isLoading = true

        disposable = apiClient.bringBoard(0, pageNumber)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    if (it.board.isNotEmpty()) {
                        mAdapter.addAllItems(it.board)
                        pageNumber += it.board.size
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    if(isFirstLoad) {
                        isFirstLoad = false
                    }
                    mAdapter.notifyChanged()
                    isLoading = false
                    view.progressBar(1)
                }
                .doOnError {
                    it.printStackTrace()
                    isLoading = false
                    view.progressBar(1)
                }
                .subscribe()
    }

    override fun loadMore() {
        val linearLayoutManager: LinearLayoutManager = view.recyclerView().layoutManager as LinearLayoutManager

        view.recyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!isLoading && linearLayoutManager.itemCount - 1 == linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                    Log.e("1", "1")
                    setUpData()
                }
                /*if (!isLoading && linearLayoutManager.itemCount - 1 == linearLayoutManager.findLastVisibleItemPosition()) {

                }*/
            }
        })
    }

    override fun disposableClear() = disposable!!.dispose()
}
