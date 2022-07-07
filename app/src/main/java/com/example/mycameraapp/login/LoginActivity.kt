package com.example.mycameraapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.mycameraapp.main.MainActivity
import com.example.mycameraapp.R
import com.example.mycameraapp.databinding.ActivityLoginBinding
import com.example.mycameraapp.session.Session
import com.example.mycameraapp.session.SessionManager
import com.example.mycameraapp.session.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLogoutButton()
    }



    private fun clickLogoutButton(){
        binding.btnLogin.setOnClickListener {
            if (onValid()){
                storeUserSessionInSharePref()
                goToManiActivity()
            }
        }
    }



    private fun goToManiActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }




    private fun onValid(): Boolean {
        if (TextUtils.isEmpty(getUserName())) {
            binding.etUsername.error = resources.getString(R.string.invalid_username)
            return false
        }


        if (TextUtils.isEmpty(getPassword())) {
            binding.etPassword.error = resources.getString(R.string.invalid_password)
            return false
        }

        return true
    }



    private fun getUserName(): String {
        return binding.etUsername.text.toString()
    }

    private fun getPassword(): String {
        return binding.etPassword.text.toString()
    }

    private fun storeUserSessionInSharePref(){
        val sessionManager = SessionManager.getInstance(this)
        val session = Session()
        session.user = User(
            binding.etUsername.text.toString()
        )
        sessionManager!!.persist(session)
    }

}