package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.*
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {


    private lateinit var mPresenter: LoginPresenter

    private lateinit var mGoogleApiClient: GoogleApiClient

    //Google Login 관련
    private val RC_SIGN_IN = 10
    private var Google_key: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        mPresenter = LoginPresenter()

        mPresenter.setView(this, this)

        //setUpGoogleLogin()
        mPresenter.setUpGoogleLogin()

        onClickEvent()
    }

    private fun onClickEvent() {
        login_signIn_button.setOnClickListener {
            Log.e("1", "1")
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mPresenter.mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    //Google Login 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e("2", "2")

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                mPresenter.firebaseAuthWithGoogle(account!!)
            } else {

            }
        }
    }

    override fun startActivity(activityClass: Class<*>, google_id: String) {
        val intent = Intent(this, activityClass)

        if(google_id.isNotEmpty()) {
            intent.putExtra("google_id", google_id)
            startActivity(intent)
        } else {
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()

        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
