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
import android.text.TextUtils
import android.util.Log
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity
import com.tistory.jeongs0222.namdaein.ui.activity.termsofuse.TermsOfUseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginPresenter: LoginContract.Presenter, GoogleApiClient.OnConnectionFailedListener, FacebookCallback<LoginResult> {

    private lateinit var view: LoginContract.View
    private lateinit var context: Context

    private lateinit var activity: LoginActivity

    private var compositeDisposable = CompositeDisposable()

    private lateinit var dbHelper: DBHelper

    private lateinit var connectModel: String

    //Google Login 관련
    lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mAuth: FirebaseAuth

    private val apiClient by lazy { ApiClient.create()}


    override fun setView(view: LoginContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    //사용자 정보가 있으면 자동으로 넘어감
    override fun validateUserInfo() {
        val google_uId = dbHelper.getGoogle_uId()

        if(!(TextUtils.isEmpty(google_uId))) {
            view.startActivity(MainActivity::class.java, "")
        }

    }

    //Google Login 관련
    override fun setUpGoogleLogin() {
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

    //Google Login 관련
    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    //Google Login 관련
    override fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        val google_uId = user!!.uid

                        connectModel = "Google"

                        keyCheck(google_uId)
                    }
                }
    }

    //facebook Login 관련
    override fun setUpFacebookLogin(activity: LoginActivity) {
        this.activity = activity

        view.facebookButton().apply {
            setReadPermissions("email", "public_profile")
            registerCallback(view.mCallbackManager(), this@LoginPresenter)
        }

    }

    //Facebook Login 관련
    override fun onSuccess(result: LoginResult?) {
        handleFacebookAccessToken(result!!.accessToken)
    }

    override fun onCancel() {

    }

    override fun onError(error: FacebookException?) {

    }

    //Facebook Login 관련
    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential: AuthCredential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if(task.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            val google_uId = user!!.uid

                            connectModel = "Facebook"

                            keyCheck(google_uId)
                        }
                    }

                })
    }

    private fun keyCheck(google_uId: String) {
        compositeDisposable
                .add(apiClient.keyCheck(google_uId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe {
                            if(it.value == 0) {
                                view.startActivity(TermsOfUseActivity::class.java, connectModel)
                            } else if(it.value == 1) {
                                view.startActivity(TermsOfUseActivity::class.java, connectModel)
                            } else {
                                insertRealm(google_uId)
                            }
                        }
                )
    }

    private fun insertRealm(google_uId: String) {
        compositeDisposable
                .add(apiClient.userInfo(google_uId)
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            if(it.value == 0) {
                                dbHelper.insert(google_uId, it.nickname, "on", connectModel)
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { it.printStackTrace() }
                        .subscribe {
                            view.startActivity(MainActivity::class.java, "")
                        }
                )
    }

    override fun disposableClear() = compositeDisposable.clear()

}