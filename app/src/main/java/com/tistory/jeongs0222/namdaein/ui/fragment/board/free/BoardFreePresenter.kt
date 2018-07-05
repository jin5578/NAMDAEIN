package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.fragment.board.BoardItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardFreePresenter: BoardFreeContract.Presenter {

    private lateinit var view: BoardFreeContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private lateinit var item: MutableList<Model.boardItem>
    private var items: MutableList<Object> = ArrayList()

    private var pageNumber: Int = 0

    private val FIRST_LOAD = 0
    private val MORE_LOAD = 1

    private var isLoading = false

    private lateinit var mAdapter: BoardItemAdapter

    private val apiClient by lazy {
        ApiClient.create()
    }


    override fun setView(view: BoardFreeContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpRecyclerView() {
        view.recyclerView().apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }
    }

    override fun setUpData(loadValue: Int) {

        view.progressBar().visibility = View.VISIBLE

        disposable = apiClient.bringBoard(0, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    item = it.board
                }
                .doOnComplete {
                    if(loadValue == FIRST_LOAD) {
                        for (i in item.indices) {
                            items.add(item[i] as Object)
                        }

                        mAdapter = BoardItemAdapter(items, context)

                        view.recyclerView().adapter = mAdapter

                        mAdapter.notifyDataSetChanged()

                        pageNumber += item.size

                    } else if(loadValue == MORE_LOAD) {
                        if(item.size > 0) {
                            for(i in item.indices) {
                                mAdapter.addItems(item.get(i))
                            }

                            isLoading = false
                            pageNumber += item.size
                        }
                    }
                }
                .subscribe(
                        {view.progressBar().visibility = View.GONE}
                )
    }

    override fun loadMore() {
        val linearLayoutManager: LinearLayoutManager = view.recyclerView().layoutManager as LinearLayoutManager
        view.recyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(linearLayoutManager.itemCount == linearLayoutManager.findLastVisibleItemPosition()) {

                } else if(isLoading == false && linearLayoutManager.itemCount - 1 == linearLayoutManager.findLastVisibleItemPosition()) {
                    isLoading = true
                    setUpData(MORE_LOAD)
                }
            }
        })
    }
}
