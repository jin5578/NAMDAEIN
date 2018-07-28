package com.tistory.jeongs0222.namdaein.utils

import android.support.v4.app.FragmentManager
import android.util.Log
import com.gun0912.tedpermission.PermissionListener
import java.util.ArrayList

class PermissionUtil(private val mCallback: PermissionCallbackListener, private val supportFragment: FragmentManager)
    : PermissionListener {

    override fun onPermissionGranted() {
        mCallback.onSuccess(supportFragment)
    }

    override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
        mCallback.onFail(deniedPermissions)
    }

}