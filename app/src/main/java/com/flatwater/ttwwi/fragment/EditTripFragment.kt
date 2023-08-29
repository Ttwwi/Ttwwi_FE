package com.flatwater.ttwwi.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.flatwater.ttwwi.databinding.FragmentEditTripBinding
import com.flatwater.ttwwi.fragment.feed.TripFeedActivity
import com.flatwater.ttwwi.viewobjects.EditTripViewObject

// EditTripPage 작업 예정 (8/18 ~ )
class EditTripFragment : Fragment() {

    lateinit var binding: FragmentEditTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditTripBinding.inflate(inflater, container, false)


//        0. 여행 제작 페이지
//        - 프로필 사진 등록 : 버튼 클릭 - 갤러리 접근 - 선택한 이미지 프로필 사진란에 랜더링 - 사진 등록하기 버튼 클릭 시 프로필 이미지 uri 데이터 모델에 저장
//        - 비밀번호 : 정규표현식 사용하여 로직 부여 (예 : 8 ~ 16자 / 영문 + 숫자 + 특수문자 혼용)
//        - 소셜로그인 친구초대 기능 구현 : 소셜 로그인 친구 초대 버튼 클릭 시 연결되어 있는 소셜 로그인의 친구 초대할 수 있는 기능 구현 (해당 소셜 로그인 기능 구현 시 적용)
//        - 제목 / 부제 / 설명 / 비밀번호 / 친구 목록 (새로 추가된 내용) 데이터 모델 적용

        // 제목 입력 -> 여행 표지 페이지에 반영
        val title = binding.tripName.toString()
        println(title)

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