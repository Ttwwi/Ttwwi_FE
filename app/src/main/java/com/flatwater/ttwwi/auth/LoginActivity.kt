package com.flatwater.ttwwi.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.flatwater.ttwwi.MainActivity
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.socialLoginKakaoBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // kakao 소셜 로그인 연결 예정
        }

        binding.socialLoginFacebookBtn.setOnClickListener {
            // facebook 소셜 로그인 연결 예정
        }

    }
}