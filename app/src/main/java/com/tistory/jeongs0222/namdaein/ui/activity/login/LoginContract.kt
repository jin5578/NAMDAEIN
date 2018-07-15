package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


interface LoginContract {

    interface View {
        fun startActivity(activityClass: Class<*>, google_id: String)
    }

    interface Presenter {
        fun setView(view: View, context: Context)

        fun setUpGoogleLogin()

        fun firebaseAuthWithGoogle(acct: GoogleSignInAccount)
    }
}