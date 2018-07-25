package com.tistory.jeongs0222.namdaein.ui.activity.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.facebook.*
import com.google.android.gms.auth.api.Auth
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.login.widget.LoginButton
import com.tistory.jeongs0222.namdaein.utils.CustomToast


class LoginActivity : AppCompatActivity(), LoginContract.View {


    private lateinit var mPresenter: LoginPresenter

    //Facebook Login 관련
    private lateinit var mCallbackManager: CallbackManager

    //Google Login 관련
    private val RC_SIGN_IN = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        mPresenter = LoginPresenter()

        mPresenter.setView(this, this)

        mPresenter.validateUserInfo()

        mPresenter.setUpGoogleLogin()

        onClickEvent()
    }

    private fun onClickEvent() {
        login_google_button.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mPresenter.mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        login_facebook_button.setOnClickListener {
            mCallbackManager = CallbackManager.Factory.create()
            mPresenter.setUpFacebookLogin( this@LoginActivity)
        }
    }


    //Google Login 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                mPresenter.firebaseAuthWithGoogle(account!!)
            } else {

            }
        } else {
            //Facebook Login 관련
            mCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun startActivity(activityClass: Class<*>, connectModel: String) {
        val intent = Intent(this, activityClass)

        if(connectModel != "") {
            intent.putExtra("connectModel", connectModel)
            startActivity(intent)
        } else {
            startActivity(intent)
        }

        finish()
    }

    override fun facebookButton(): LoginButton {
        return login_facebook_button
    }

    override fun mCallbackManager(): CallbackManager {
        return mCallbackManager
    }

    override fun toastMessage(message: String) {
        val toastMessage = CustomToast(this)

        toastMessage.makeText(message, Toast.LENGTH_SHORT)
    }

    override fun onStop() {
        mPresenter.disposableClear()

        super.onStop()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}
