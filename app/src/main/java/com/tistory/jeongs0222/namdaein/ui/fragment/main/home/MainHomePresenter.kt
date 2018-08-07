package com.tistory.jeongs0222.namdaein.ui.fragment.main.home

import android.content.Context
import android.support.v7.widget.*
import com.tistory.jeongs0222.namdaein.api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainHomePresenter: MainHomeContract.Presenter {

    private lateinit var view: MainHomeContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

    private lateinit var mAdapter: NewsItemAdapter

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: MainHomeContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitNews() {
        mAdapter = NewsItemAdapter(context)

        view.newsRecyclerView().apply {
            adapter = mAdapter

            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            scrollToPosition(0)
        }

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(view.newsRecyclerView())
    }

    override fun setUpNewsData() {
        compositeDisposable
                .add(apiClient.campusNews()
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            if(it.news.isNotEmpty()) {
                                mAdapter.addAllItems(it.news)
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe()
                )
    }
}