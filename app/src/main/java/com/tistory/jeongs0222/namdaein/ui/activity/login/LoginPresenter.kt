package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.namdaein.R


class LoginPresenter: LoginContract.Presenter {

    private lateinit var view: LoginContract.View
    private lateinit var context: Context

    private lateinit var mAuth: FirebaseAuth

    override fun setView(view: LoginContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    /*override fun setUpGoogleLogin() {
        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }*/

}