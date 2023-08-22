package com.flatwater.ttwwi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatwater.ttwwi.databinding.FragmentEditTripBinding
import com.flatwater.ttwwi.fragment.feed.TripFeedActivity

// EditTripPage 작업 예정 (8/18 ~ )
class EditTripFragment : Fragment() {

    lateinit var binding: FragmentEditTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTripBinding.inflate(inflater, container, false)

        // 여행 피드 페이지로 연결되도록 설정 (임시)
        binding.makeTripBtn.setOnClickListener {
            val intent = Intent(activity, TripFeedActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }
}