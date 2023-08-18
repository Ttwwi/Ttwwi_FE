package com.flatwater.ttwwi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatwater.ttwwi.databinding.FragmentEditTripBinding

// EditTripPage 작업 예정 (8/18 ~ )
class EditTripFragment : Fragment() {

    lateinit var binding: FragmentEditTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTripBinding.inflate(inflater, container, false)
        return binding.root
    }
}