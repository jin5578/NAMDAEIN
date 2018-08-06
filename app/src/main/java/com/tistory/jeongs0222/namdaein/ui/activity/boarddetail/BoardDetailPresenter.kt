package com.tistory.jeongs0222.namdaein.ui.activity.boarddetail

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.CommentAdapter
import com.tistory.jeongs0222.namdaein.ui.activity.PictureViewPagerAdapter
import com.tistory.jeongs0222.namdaein.utils.DateUtil
import com.tistory.jeongs0222.namdaein.utils.DynamicViewUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class BoardDetailPresenter: BoardDetailContract.Presenter, TextWatcher, ViewPager.OnPageChangeListener {

    private lateinit var view: BoardDetailContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

    private var order: Int = 0

    private lateinit var mAdapter: CommentAdapter

    private lateinit var pAdapter: PictureViewPagerAdapter

    private lateinit var writtenUserKey: String

    private var images: MutableList<String> = ArrayList()

    private lateinit var dots: ArrayList<ImageView>

    private lateinit var dbHelper: DBHelper

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: BoardDetailContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpInitData(order: Int, callback: (Model.boardItem) -> Unit) {
        this.order = order

        view.progressBar(0)

        compositeDisposable
                .add(apiClient.bringBoardDetail(order)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        it.printStackTrace()

                        view.progressBar(1)
                    }
                    .subscribe {
                        if(it.image0.isEmpty()) {
                            view.imageViewPagerVisible(1)
                        } else {
                            view.imageViewPagerVisible(0)
                        }

                        writtenUserKey = it.userkey

                        callback(it)
                    }
                )
    }

    override fun pictureViewPager(images: MutableList<String>) {
        this.images = images

        pAdapter = PictureViewPagerAdapter(context, images)

        view.imageViewPager().adapter = pAdapter
    }

    override fun addDots() {
        DynamicViewUtil.showDots(images.size, context, view.dotsLinearLayout()) {
            dots = it

            view.imageViewPager().addOnPageChangeListener(this@BoardDetailPresenter)
        }
    }

    //PageChangeListener
    override fun onPageSelected(position: Int) {
        for(j in 0..images.size - 1) {
            dots[j].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))
        }

        dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

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
        compositeDisposable
                .add(apiClient.bringBoardComment(order)
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

                )
    }

    override fun setUpCommentFunc() {
        view.sendEditText().addTextChangedListener(this@BoardDetailPresenter)
    }

    override fun setUpFavoriteFunc() {
        compositeDisposable
                .add(apiClient.writingFavorite(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            if(it.value == 0) {
                                view.favoriteClickable(0)
                            }
                        }
                        .doOnError {
                            it.printStackTrace()
                        }
                        .subscribe()
                )
    }

    override fun setUpReportFunc() {
        compositeDisposable
                .add(apiClient.writingReport(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            if(it.value == 0) {
                                view.reportClickable(0)
                            }
                        }
                        .doOnError {
                            it.printStackTrace()
                        }
                        .subscribe())
    }

    override fun setUpSendFunc() {
        if(view.sendEditText().text.isNotEmpty()) {
            compositeDisposable
                    .add(apiClient.writingBoardComment(order, dbHelper.getGoogle_uId()!!, view.sendEditText().text.toString(), DateUtil.bringDate())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete {
                            setUpCommentData()
                        }
                        .doOnError { it.printStackTrace() }
                        .subscribe {
                            if(it.value == 1) {
                                view.toastMessage(it.message)
                            } else {
                                commentPush()
                            }
                            view.sendEditText().text = null
                            view.sendClickable(0)
                        }
                    )
        }
    }

    private fun commentPush() {
        if(dbHelper.getGoogle_uId() != writtenUserKey) {
            compositeDisposable
                    .add(apiClient.push(writtenUserKey, dbHelper.getNickname()!!, 1)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError { it.printStackTrace() }
                            .subscribe({Log.e("value", it.value.toString())})
                    )

        }
    }

    override fun disposableClear() = compositeDisposable.clear()

    //TextChangeListener
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
}