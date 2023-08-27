package com.flatwater.ttwwi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flatwater.ttwwi.R
import com.flatwater.ttwwi.databinding.RvItemPostBinding
import com.flatwater.ttwwi.fragment.feed.PostInformation

class PostRVAdapter(val postDataList : ArrayList<PostInformation>) : RecyclerView.Adapter<PostRVAdapter.Holder>() {

    inner class Holder(val binding: RvItemPostBinding) : RecyclerView.ViewHolder(binding.root){
        val publisherProfilePhoto = binding.rvPublisherProfilePhoto
        val publisherProfileName = binding.rvPublisherProfileName
        val postDescription = binding.rvPostDescription
        val userProfilePhoto = binding.rvUserProfilePhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostRVAdapter.Holder {
        val binding = RvItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: PostRVAdapter.Holder, position: Int) {
        holder.publisherProfileName.text = postDataList[position].publisherProfileName
        holder.postDescription.text = postDataList[position].publisherDescription

        // #P : backend server와 통신 예정 (post publisher -> not user)
        holder.publisherProfilePhoto.setImageResource(R.drawable.contributor1_small_icon)
        holder.userProfilePhoto.setImageResource(R.drawable.contributor2_small_icon)
    }

    override fun getItemCount(): Int {
        return postDataList.size
    }

}