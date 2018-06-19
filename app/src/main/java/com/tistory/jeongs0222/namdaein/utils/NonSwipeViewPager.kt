package com.tistory.jeongs0222.namdaein.utils

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller

class NonSwipeViewPager(context: Context, attrs: AttributeSet?): ViewPager(context, attrs) {

    init {

        setMyScroller()

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        return false

    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {

        return false

    }

    private fun setMyScroller() {

        try {

            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")

            scroller.isAccessible = true
            scroller.set(this, MyScroller(context))

        } catch (e: Exception) {

            e.printStackTrace()

        }
    }

    inner class MyScroller(context: Context) : Scroller(context, DecelerateInterpolator()) {

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {

            super.startScroll(startX, startY, dx, dy, 350)

        }
    }
}