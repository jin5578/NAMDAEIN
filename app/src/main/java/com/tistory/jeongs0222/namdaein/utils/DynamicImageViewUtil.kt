package com.tistory.jeongs0222.namdaein.utils

import android.content.Context
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.namdaein.R

object DynamicImageViewUtil{

    fun xdpx(context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, context.resources.displayMetrics).toInt()
    fun hdpx(context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, context.resources.displayMetrics).toInt()

    fun showUri(uri: Uri, context: Context, requestManager: RequestManager, success: (View) -> Unit){
        val imageHolder = LayoutInflater.from(context).inflate(R.layout.item_image, null)
        val thumbnail = imageHolder.findViewById(R.id.media_image) as ImageView

        requestManager.load(uri.toString())
                .apply(RequestOptions().fitCenter())
                .into(thumbnail)

        thumbnail.layoutParams = FrameLayout.LayoutParams(DynamicImageViewUtil.xdpx(context), DynamicImageViewUtil.hdpx(context))

        success(imageHolder)

    }
}