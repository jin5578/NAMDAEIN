package com.tistory.jeongs0222.namdaein.utils

import android.support.v4.app.FragmentManager
import java.util.ArrayList


interface PermissionCallbackListener {

    fun onSuccess(supportFragment: FragmentManager)
    fun onFail(deniedPermissions: ArrayList<String>?)
}