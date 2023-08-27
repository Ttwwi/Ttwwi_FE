package com.flatwater.ttwwi.fragment.feed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityTripFeedBinding
import com.kakao.sdk.user.UserApiClient

class TripFeedActivity : AppCompatActivity() {

    lateinit var binding : ActivityTripFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_feed)

        // #P : 사용자가 로그인한 계정의 친구 초대 페이지로 넘어갈 수 있게 기능 구현 예정
        binding.inviteFriendBtn.setOnClickListener {
            Toast.makeText(this, "친구 초대 페이지로 이동합니다!", Toast.LENGTH_SHORT).show()
        }

        // #P : 실시간 채팅 페이지로 이동 기능 구현 (실시간 채팅 기능 구현 시 함께 구현 예정)
        binding.chatBtn.setOnClickListener {
            Toast.makeText(this, "실시간 채팅 페이지로 이동합니다!", Toast.LENGTH_SHORT).show()
        }

        // #C : 게시글 제작 페이지로 넘어갈 수 있도록 이벤트 처리
        binding.makePostBtn.setOnClickListener {
            val intent = Intent(this, MakePostActivity::class.java)
            startActivity(intent)
        }

        // #M : Post 목록 피드 페이지에 랜더링
        // 사용자 정보 획득

        val postList = ArrayList<PostInformation>()

    }
}