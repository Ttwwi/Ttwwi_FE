package com.flatwater.ttwwi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.flatwater.ttwwi.databinding.ProfileEditModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProfileEditBottomSheet(context: Context) : BottomSheetDialogFragment() {

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

        val editNicknameDialog = EditNicknameDialog(requireContext())
        binding.editNickname.setOnClickListener {
            // #P : 닉네임 변경하는 다이얼로그 바인딩 (initialize button 클릭했을 때 발생하는 "Attempt to invoke virtual method 'java.lang.String android.content.Context.getPackageName()' on a null object reference" 문제 해결하여야 함!)
            editNicknameDialog.showEditNicknameDialog()
            editNicknameDialog.setOnClickedListener(object : EditNicknameDialog.SaveButtonClickListener {
                override fun onClicked(newNickname: String) {
                    if(newNickname.length > 0) {
                        Toast.makeText(activity, "닉네임에 반영할게요!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "닉네임에 반영하지 않을게요!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    companion object {
        const val TAG = "ProfileBottomModalSheet"
    }
}