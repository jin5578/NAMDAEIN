package com.tistory.jeongs0222.namdaein.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tistory.jeongs0222.namdaein.ui.intro.IntroActivity

class SplashActivity : AppCompatActivity() {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        skip()
    }

    private fun skip() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)

        finish()
    }
}