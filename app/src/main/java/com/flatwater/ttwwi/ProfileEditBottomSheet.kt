package com.flatwater.ttwwi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatwater.ttwwi.databinding.ProfileEditModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProfileEditBottomSheet() : BottomSheetDialogFragment() {

    lateinit var binding: ProfileEditModalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ProfileEditModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editPhoto.setOnClickListener {
            // #P : 갤러리 앱으로 연결
            dismiss()
        }

        binding.editNickname.setOnClickListener {
            // #P : 닉네임 변경하는 다이얼로그 바인딩
            dismiss()
        }
    }

    companion object {
        const val TAG = "ProfileBottomModalSheet"
    }
}