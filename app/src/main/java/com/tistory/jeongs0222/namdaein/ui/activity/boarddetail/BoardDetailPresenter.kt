package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context
import android.util.Log
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BoardDetailPresenter: BoardDetailContract.Presenter {

    private lateinit var view: BoardDetailContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var order: Int = 0

    private lateinit var images: MutableList<String>

    private val apiClient by lazy {
        ApiClient.create()
    }

    override fun setView(view: BoardDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (String, Model.boardItem) -> Unit) {
        this.order = order

        view.progressBar(0)

        disposable = apiClient.bringBoardDetail(order)
                .subscribeOn(Schedulers.io())
                /*.doOnNext {
                    if(it.image0.isNotEmpty()) {
                        images.add(it.image0)

                        if(it.image1.isNotEmpty()) {
                            images.add(it.image1)

                            if(it.image2.isNotEmpty()) {
                                images.add(it.image2)
                            }
                        }
                    }
                }*/
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {

                }
                .doOnError {
                    it.printStackTrace()

                    view.progressBar(1)
                }
                .subscribe({
                    if(it.image0.isEmpty()) {
                        view.imageViewPagerVisible(1)
                    } else {
                        view.imageViewPagerVisible(0)
                    }

                    callback("complete", it)
                })
    }
}