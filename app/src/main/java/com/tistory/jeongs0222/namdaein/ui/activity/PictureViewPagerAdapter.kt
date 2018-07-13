package com.tistory.jeongs0222.namdaein.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.utils.ImageViewPager
import kotlinx.android.synthetic.main.picture_image.view.*


class PictureViewPagerAdapter(val context: Context, val images: MutableList<String>): PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.picture_image, null)

        val imageView = view.findViewById<ImageView>(R.id.picture_item)

        var viewPager: ViewPager = container as ViewPager

        Glide.with(context)
                .asBitmap()
                .load(images.get(position))
                .apply(RequestOptions()
                        .encodeFormat(Bitmap.CompressFormat.PNG)
                        .placeholder(R.drawable.ic_refresh_black_24dp)
                        .error(R.drawable.ic_refresh_black_24dp)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .into(imageView)

        //이 부분에 이미지 전체화면으로 보여주는 부분 넣어줘야한다.
        imageView.setOnClickListener {
            val intent = Intent(it.context, ImageViewPager::class.java)
            intent.putStringArrayListExtra("imagesList", images as ArrayList<String>)
            it.context.startActivity(intent)
        }

        viewPager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View

        container.removeView(view)
    }


}