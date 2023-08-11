package com.flatwater.ttwwi.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.flatwater.ttwwi.MainActivity
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivitySignUpCompleteBinding
import com.kakao.sdk.user.UserApiClient

class SignUpCompleteActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_complete)

        val nickname = binding.socialLoginUserNickname

        UserApiClient.instance.me { user, error ->
            nickname.text = "${user?.kakaoAccount?.profile?.nickname}"
        }

        // 확인 버튼 클릭시 메인페이지 이동
        binding.startBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}