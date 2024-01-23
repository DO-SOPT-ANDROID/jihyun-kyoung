package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(myProfileData: Profile) {
        if (myProfileData.profileImage != null)
            binding.ivProfile.setImageResource(myProfileData.profileImage)
        binding.tvName.text = myProfileData.nickname
    }
}