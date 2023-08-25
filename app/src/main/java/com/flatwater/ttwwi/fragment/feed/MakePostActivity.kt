package com.flatwater.ttwwi.fragment.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityMakePostBinding

class MakePostActivity : AppCompatActivity() {

    lateinit var binding : ActivityMakePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_make_post)



    }
}