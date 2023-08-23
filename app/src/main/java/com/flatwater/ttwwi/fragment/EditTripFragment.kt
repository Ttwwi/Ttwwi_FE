package com.flatwater.ttwwi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.flatwater.ttwwi.databinding.FragmentEditTripBinding
import com.flatwater.ttwwi.fragment.feed.TripFeedActivity

// EditTripPage 작업 예정 (8/18 ~ )
class EditTripFragment : Fragment() {

    lateinit var binding: FragmentEditTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditTripBinding.inflate(inflater, container, false)

        // 제목 입력 -> 여행 표지 페이지에 반영

        // 비밀번호 입력 -> 여행 표지 페이지에 입력해야 들어갈 수 있도록 설정

        // 이미지 아이콘 터치 -> 갤러리 & 카메라 연결 -> 사진 등록 버튼 터치 -> 프로필 사진 등록 완료
        binding.tripProfilePhotoRegisterButton.setOnClickListener {
            // 갤러리로 연결 (사진 선택해서 binding.tripProfilePhoto로 데이터 주입)
        }

        // 소셜 친구 초대 (소셜 로그인 기능 연결된 후 연결 예정)
        binding.kakaoInviteFriend.setOnClickListener {
            Toast.makeText(activity, "kakao 친구 초대 기능 구현 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.instagramInviteFriend.setOnClickListener {
            Toast.makeText(activity, "instagram 팔로워 초대 기능 구현 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        // 부제 & 설명 -> 여행 표지 페이지에 반영 (선택 사항)

        // 여행 피드 페이지로 연결되도록 설정 (임시)
        binding.makeTripBtn.setOnClickListener {
            val intent = Intent(activity, TripFeedActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }
}