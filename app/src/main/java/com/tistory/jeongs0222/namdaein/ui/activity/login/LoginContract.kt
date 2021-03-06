package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Context
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


interface LoginContract {

    interface View {
        fun startActivity(activityClass: Class<*>, connectModel: String)

        fun facebookButton(): com.facebook.login.widget.LoginButton

        fun mCallbackManager(): CallbackManager

        fun toastMessage(message: String)

        fun progressBar(value: Int)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun validateUserInfo()

        fun setUpGoogleLogin()

        fun firebaseAuthWithGoogle(acct: GoogleSignInAccount)

        fun setUpFacebookLogin(activity: LoginActivity)

        fun disposableClear()
    }
}