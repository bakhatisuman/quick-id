package com.example.mycameraapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.mycameraapp.MainActivity
import com.example.mycameraapp.databinding.ActivitySplashScreenBinding
import com.example.mycameraapp.login.LoginActivity
import com.example.mycameraapp.session.SessionManager

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        delayForSomeSecond()
        super.onStart()
    }



    private fun checkSessionAndDeclareWhereToGo(){
        val sessionManager = SessionManager.getInstance(this)
        val session = sessionManager!!.findOne()
        if (session == null) {
            goToLoginActivity()
        } else {
            goToMainActivity()
        }

    }


    private fun delayForSomeSecond(){
        Handler(Looper.getMainLooper()).postDelayed(
            {checkSessionAndDeclareWhereToGo()
            },
            100 // value in milliseconds
        )
    }


    private fun goToLoginActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }


    private fun goToMainActivity(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}