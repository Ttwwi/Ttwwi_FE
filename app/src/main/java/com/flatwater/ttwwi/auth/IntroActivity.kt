package com.flatwater.ttwwi.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        binding.viewPagerAppIntroduction.adapter = ViewPagerAdapter(getAppIntroduction())
        binding.viewPagerAppIntroduction.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.socialLoginKakaoBtn.setOnClickListener {
            // kakao 소셜 로그인 연결 예정
        }

        binding.socialLoginFacebookBtn.setOnClickListener {
            // facebook 소셜 로그인 연결 예정
        }
    }

    private fun getAppIntroduction(): ArrayList<Int> {
        return arrayListOf<Int> (
            R.drawable.illustration1,
            R.drawable.illustration2,
            R.drawable.illustration3
        )
    }
}
