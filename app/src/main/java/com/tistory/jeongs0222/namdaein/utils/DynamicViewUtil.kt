package com.tistory.jeongs0222.namdaein.utils

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.namdaein.R

object DynamicViewUtil{

    fun xdpx(context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, context.resources.displayMetrics).toInt()
    fun hdpx(context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, context.resources.displayMetrics).toInt()

    fun showUri(uri: Uri, context: Context, requestManager: RequestManager, success: (View) -> Unit){
        val imageHolder = LayoutInflater.from(context).inflate(R.layout.item_image, null)
        val thumbnail = imageHolder.findViewById(R.id.media_image) as ImageView

        requestManager.load(uri.toString())
                .apply(RequestOptions().fitCenter())
                .into(thumbnail)

        thumbnail.layoutParams = FrameLayout.LayoutParams(DynamicViewUtil.xdpx(context), DynamicViewUtil.hdpx(context))

        success(imageHolder)
    }

    fun showDots(dotsCount: Int, context: Context, dotsLinearLayout: LinearLayout, success: (ArrayList<ImageView>) -> Unit) {
        if (dotsCount > 0) {
            var dots = ArrayList<ImageView>()

            for (i in 0..dotsCount - 1) {
                var imageView = ImageView(context)
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))

                dots.add(imageView)

                var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(5, 0, 5, 0)

                dotsLinearLayout.addView(dots.get(i), params)
            }

            dots.get(0).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))

            success(dots)
        }
    }
}