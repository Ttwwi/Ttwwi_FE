package com.flatwater.ttwwi.fragment.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.ActivityTripFeedBinding

class TripFeedActivity : AppCompatActivity() {

    lateinit var binding : ActivityTripFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_feed)


    }
}