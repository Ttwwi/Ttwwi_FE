package com.flatwater.ttwwi

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView

class EditNicknameDialog(context: Context) {

    private val editNicknameDialog = Dialog(context)

    fun showEditNicknameDialog() {

        editNicknameDialog.setContentView(R.layout.edit_nickname_dialog)

        editNicknameDialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

        editNicknameDialog.setCanceledOnTouchOutside(false)
        editNicknameDialog.setCancelable(true)

        editNicknameDialog.show()

        val closeBtn = editNicknameDialog.findViewById<ImageButton>(R.id.close_btn)
        val editedNickname = editNicknameDialog.findViewById<EditText>(R.id.new_nickname)
        val initializeBtn = editNicknameDialog.findViewById<ImageView>(R.id.initialize_btn)
        val saveBtn = editNicknameDialog.findViewById<Button>(R.id.edit_nickname_save_btn)


        // #C : Dialog 상단 닫기 버튼 기능 구현
        closeBtn.setOnClickListener {
            editNicknameDialog.dismiss()
        }

        // #C : Dialog 하단 초기화 버튼 기능 구현 (초기화되지 않고 앱이 죽는 현상 발생 -> 추후 수정 예정)
        initializeBtn.setOnClickListener {
            onClickedListener.onClicked(editedNickname.setText(null).toString())
        }

        // #C : Dialog 하단 새로 입력한 닉네임 String 변환 및 닫기 버튼 구현
        saveBtn.setOnClickListener {
            onClickedListener.onClicked(editedNickname.text.toString())
            editNicknameDialog.dismiss()
        }
    }

    interface SaveButtonClickListener {
        fun onClicked(newNickname: String)
    }

    private lateinit var onClickedListener: SaveButtonClickListener

    fun setOnClickedListener(listener: SaveButtonClickListener) {
        onClickedListener = listener
    }
}