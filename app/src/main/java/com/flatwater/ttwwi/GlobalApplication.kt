package com.flatwater.ttwwi

import android.app.Application
import com.flatwater.ttwwi.R
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
    }
}