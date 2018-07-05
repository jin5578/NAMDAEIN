package com.tistory.jeongs0222.namdaein.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.tistory.jeongs0222.namdaein.ui.activity.login.LoginActivity
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        skip()
    }

    private fun skip() {
        Handler().postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)

            finish()
        }, 1000)
    }
}