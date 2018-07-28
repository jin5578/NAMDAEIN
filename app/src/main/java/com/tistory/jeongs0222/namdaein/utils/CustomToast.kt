package com.tistory.jeongs0222.namdaein.utils

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tistory.jeongs0222.namdaein.R


class CustomToast(val activity: Activity) {

    fun makeText(text: String, duration: Int): Toast {

        val inflater = activity.getLayoutInflater()

        val layout = inflater.inflate(R.layout.item_toast, activity.findViewById(R.id.toast_linearLayout) as?
                ViewGroup)

        val textView = layout.findViewById<TextView>(R.id.toast_textView)
        textView.text = text

        val toast = Toast(activity)
        toast.setGravity(Gravity.BOTTOM, 0, 260)
        toast.duration = duration
        toast.view = layout

        return toast
    }
}