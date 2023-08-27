package com.flatwater.ttwwi.fragment.feed

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.adapter.SelectedPhotoGalleryAdapter
import com.flatwater.ttwwi.databinding.ActivityMakePostBinding

class MakePostActivity : AppCompatActivity() {

    lateinit var binding : ActivityMakePostBinding
    lateinit var galleryAdapter: SelectedPhotoGalleryAdapter

    var imageList: ArrayList<Uri> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_make_post)

        // #M : 갤러리 연동하여 선택한 사진 랜더링해주는 기능 구현 완료

//        binding.tripProfilePhoto.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            activityResult.launch(intent)
//        }

        galleryAdapter = SelectedPhotoGalleryAdapter(imageList, this)
        binding.rvSelectedPhoto.layoutManager = LinearLayoutManager(this)
        binding.rvSelectedPhoto.adapter = galleryAdapter

        binding.chooseMultiPhotoBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            // Multi Choose Feature
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            activityResult.launch(intent)
        }

    }

    /**
    private val activityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == RESULT_OK && it.data != null) {
            val uri = it.data!!.data
            Glide.with(this)
                .load(uri)
                .into(binding.selectedPhoto)
        }
    }
    **/

    private val activityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == RESULT_OK) {
            if(it.data!!.clipData != null) {
                val count = it.data!!.clipData!!.itemCount
                for(index in 0 until count) {
                    val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                    imageList.add(imageUri)
                }
            } else {
                val imageUri = it.data!!.data
                    imageList.add(imageUri!!)
            }
            galleryAdapter.notifyDataSetChanged()
        }
    }
}