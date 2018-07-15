package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.tistory.jeongs0222.namdaein.R
import android.app.Activity
import android.util.Log
import com.google.firebase.auth.*
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity
import com.tistory.jeongs0222.namdaein.ui.activity.termsofuse.TermsOfUseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginPresenter: LoginContract.Presenter, GoogleApiClient.OnConnectionFailedListener {


    private lateinit var view: LoginContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mAuth: FirebaseAuth
    //private var google_id: String? = null

    private val apiClient by lazy { ApiClient.create()}


    override fun setView(view: LoginContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpGoogleLogin() {
        Log.e("3", "3")
        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(context)
                .enableAutoManage(view as FragmentActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.e("4", "4")
        val credential: AuthCredential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        val google_id = user!!.uid

                        keyCheck(google_id)
                    }
                }
    }

    private fun keyCheck(google_id: String) {
        disposable = apiClient.keyCheck(google_id)
                .subscribeOn(Schedulers.io())
                .doOnNext {

                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { }
                .doOnError { it.printStackTrace() }
                .subscribe( {
                    if(it.value == 0) {
                        view.startActivity(TermsOfUseActivity::class.java, google_id)
                    } else if(it.value == 1) {
                        view.startActivity(TermsOfUseActivity::class.java, google_id)
                    } else {
                        view.startActivity(MainActivity::class.java, null!!)
                    }
                })
    }
}