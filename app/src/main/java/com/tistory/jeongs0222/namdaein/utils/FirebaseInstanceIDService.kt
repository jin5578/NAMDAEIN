package com.tistory.jeongs0222.namdaein.utils

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.tistory.jeongs0222.namdaein.api.ApiClient


class FirebaseInstanceIDService : FirebaseInstanceIdService() {

    protected val apiService by lazy { ApiClient.create() }

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
    }

    companion object {

        private val TAG = "MyFirebaseIIDService"
    }
}