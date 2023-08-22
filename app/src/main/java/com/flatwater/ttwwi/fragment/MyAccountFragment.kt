package com.flatwater.ttwwi.fragment

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import coil.load
import com.bumptech.glide.Glide
import com.flatwater.ttwwi.ProfileEditBottomSheet
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.auth.IntroActivity
import com.flatwater.ttwwi.auth.SignUpActivity
import com.flatwater.ttwwi.databinding.FragmentMyAccountBinding
import com.kakao.sdk.user.UserApiClient

class MyAccountFragment : Fragment() {

    lateinit var binding: FragmentMyAccountBinding

    // 추가할 권한 목록
    private val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    // 권한 허용 요청
    private val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
        results.forEach {
            if(!it.value) {
                Toast.makeText(activity, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.socialLoginUserPhoto.load(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_account, container, false)

        // #C : social login (kakao) - 닉네임, 프로필 사진, 이메일 반영
        val nickname = binding.socialLoginUserNickname
        UserApiClient.instance.me { user, error ->
            nickname.text = "${user?.kakaoAccount?.profile?.nickname}"
        }

        val profilePhoto = binding.socialLoginUserPhoto
        UserApiClient.instance.me { user, error ->
            val profileImageUrl = user?.kakaoAccount?.profile?.profileImageUrl
            Glide.with(this)
                .load(profileImageUrl)
                .circleCrop()
                .into(profilePhoto)
        }

        val email = binding.socialLoginEmail
        UserApiClient.instance.me { user, error ->
            email.text = "${user?.kakaoAccount?.email}"
        }

        // #C : 로그아웃 회원탈퇴 기능 구현
        binding.logoutBtn.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if(error != null) {
                    Log.d("Logout Fail", "로그아웃 실패 $error")
                } else {
                    Log.d("Logout Success", "로그아웃 성공")
                }
                val intent = Intent(activity, IntroActivity::class.java)
                startActivity(intent)
            }
        }

        // #C : 카카오 회원탈퇴 기능 구현
        binding.unlinkBtn.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if(error != null) {
                    Log.d("Unlink Fail", "회원 탈퇴 실패 $error")
                } else {
                    Log.d("Unlink Success", "회원 탈퇴 성공")
                    val intent = Intent(activity, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        // #M : 권한 요청 코드 (어플 설치할 때로 시점 변경하여야 함!)
        checkPermission.launch(permissionList)


        // #C : 프로필 수정 버튼 이벤트
        binding.profileEditButton.setOnClickListener {
            // profileEditBottomSheet()
            readImage.launch("image/*")
        }

        // #E : 갤러리 연동을 잘 되지만, 사진을 바꾸지 않아도 원래 프로필 사진이 사라짐

        // #P : 여행 피드 아이템이 있으면 비행기 그림 + 안내 텍스트 제거해야함! (추후 개발 예정)

        return binding.root
    }

    // #M : 프로필 수정 버튼 새로 만들어서 다시 배치
    private fun profileEditBottomSheet() {
        val modal = ProfileEditBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.ProfileEditBottomSheetTheme)
        modal.show(childFragmentManager, ProfileEditBottomSheet.TAG)
    }

}