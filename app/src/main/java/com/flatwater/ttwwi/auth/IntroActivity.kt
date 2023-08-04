package com.flatwater.ttwwi.auth

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityIntroBinding

private const val NUM_PAGES = 5

class IntroActivity : AppCompatActivity() {

    lateinit var binding : ActivityIntroBinding

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        viewPager = binding.viewPagerAppIntroduction
        val pagerAdapter = AppIntroducePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        binding.socialLoginKakaoBtn.setOnClickListener {
            // kakao 소셜 로그인 연결 예정
        }

        binding.socialLoginFacebookBtn.setOnClickListener {
            // facebook 소셜 로그인 연결 예정
        }
    }

    private inner class AppIntroducePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> AppIntroduceFragment1()
                1 -> AppIntroduceFragment2()
                else -> AppIntroduceFragment3()
            }
        }
    }
}
