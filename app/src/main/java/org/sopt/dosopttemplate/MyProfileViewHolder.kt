package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(myProfileData: Profile) {
        binding.ivProfile.setImageResource(myProfileData.profileImage)
        binding.tvName.text = myProfileData.name
    }
}