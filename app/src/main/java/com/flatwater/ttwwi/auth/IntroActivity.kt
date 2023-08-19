package com.flatwater.ttwwi.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityIntroBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

private const val NUM_PAGES = 5

class IntroActivity : AppCompatActivity() {

    lateinit var binding : ActivityIntroBinding

    private lateinit var viewPager: ViewPager2

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance() // RetrofitHelper의 instance 호출
    private var authToken : String ?= null
    var api : LoginService = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        viewPager = binding.viewPagerAppIntroduction
        val pagerAdapter = AppIntroducePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // 카카오 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if(error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignUpCompleteActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        binding.socialLoginKakaoBtn.setOnClickListener {
            kakaoLogin()
        }

        binding.socialLoginFacebookBtn.setOnClickListener {
            // facebook 소셜 로그인 연결 예정
        }
    }

    // #P : 인가 코드 백엔드 서버로 넘겨주기 + 백엔드 서버에게 JWT 토큰과 인증 코드 전달 받기 (8/19)
    private fun kakaoLogin() {

        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 맵", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                authToken = token.accessToken
                api.postAccessToken(UserModel(accessToken = authToken)).enqueue(object : retrofit2.Callback<LoginBackendResponse> {
                    override fun onResponse(call: Call<LoginBackendResponse>, response: Response<LoginBackendResponse>) {
                        Log.d("로그인 통신 성공", response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())

                        when (response.code()) {
                            200 -> {
                                Log.d("로그인 성공", "TtwwiTtwwiTtwwiTtwwi")
                                val intent = Intent(this@IntroActivity, SignUpCompleteActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            405 -> Toast.makeText(
                                this@IntroActivity,
                                "로그인 실패 : 아이디나 비밀번호가 올바르지 않습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                            500 -> Toast.makeText(
                                this@IntroActivity,
                                "로그인 실패 : 서버 오류입니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                        Log.d("통신 로그인", "전송 실패")
                    }
                })
            }
        }

        // kakao 소셜 로그인 연결
        if(UserApiClient.instance.isKakaoTalkLoginAvailable(this@IntroActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@IntroActivity) { token, error ->
                if(error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if(error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this@IntroActivity, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@IntroActivity, callback = callback)
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
