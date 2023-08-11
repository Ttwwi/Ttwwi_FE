package com.flatwater.ttwwi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.flatwater.ttwwi.auth.IntroActivity
import com.flatwater.ttwwi.auth.SignUpActivity
import com.flatwater.ttwwi.databinding.ActivityMyPageBinding
import com.kakao.sdk.user.UserApiClient

class MyPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)

        binding.logoutBtn.setOnClickListener {
            // 로그아웃
            UserApiClient.instance.logout { error ->
                if(error != null) {
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.unlinkBtn.setOnClickListener {
            // 회원탈퇴
            UserApiClient.instance.unlink { error ->
                if(error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

    }
}