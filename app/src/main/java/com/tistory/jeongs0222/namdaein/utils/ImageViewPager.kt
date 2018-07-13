package com.tistory.jeongs0222.namdaein.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.chrisbanes.photoview.PhotoView
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.item_image_viewpager.*


class ImageViewPager: AppCompatActivity() {

    private var images: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_image_viewpager)

        init()
    }

    private fun init() {
        getValue()

        view_pager.adapter = ImagePagerAdapter(applicationContext, images)
    }

    private fun getValue() {
        val intent = intent

        images = intent.getStringArrayListExtra("imagesList")
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

    internal class ImagePagerAdapter(var context: Context, var images: MutableList<String>): PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val photoView = PhotoView(container.context)

            Glide.with(context)
                    .asBitmap()
                    .load(images.get(position))
                    .apply(RequestOptions()
                            .encodeFormat(Bitmap.CompressFormat.PNG)
                            .placeholder(R.drawable.ic_refresh_black_24dp)
                            .error(R.drawable.ic_refresh_black_24dp)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                    )
                    .into(photoView)

            container.addView(photoView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            return photoView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int = images.size

    }
}