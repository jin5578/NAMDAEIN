package com.tistory.jeongs0222.namdaein.ui.activity.capitaldetail

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.ImageView
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.ui.activity.PictureViewPagerAdapter
import com.tistory.jeongs0222.namdaein.utils.DynamicViewUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList


class CapitalDetailPresenter: CapitalDetailContract.Presenter, ViewPager.OnPageChangeListener {

    private lateinit var view: CapitalDetailContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

    private lateinit var pAdapter: PictureViewPagerAdapter

    private var images: MutableList<String> = ArrayList()

    private lateinit var dots: ArrayList<ImageView>

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: CapitalDetailContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpInitData(order: Int, callback: (Model.busTimeTable) -> Unit) {
        compositeDisposable
                .add(apiClient.capitaldetail(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe {
                            callback(it)
                        })
    }

    override fun pictureViewPager(images: MutableList<String>) {
        this.images = images

        pAdapter = PictureViewPagerAdapter(context, images)

        view.imageViewPager().adapter = pAdapter
    }

    override fun addDots() {
        DynamicViewUtil.showDots(images.size, context, view.dotsLinearLayout()) {
            dots = it

            view.imageViewPager().addOnPageChangeListener(this@CapitalDetailPresenter)
        }
    }

    //ViewPager ChangeListener
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
}