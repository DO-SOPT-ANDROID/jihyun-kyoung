package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Profile) = with(binding) {
        if (friendData.profileImage != null)
            ivProfile.setImageResource(friendData.profileImage)
        else {
            Glide.with(binding.root)
                .load(friendData.avatar)
                .into(ivProfile)
        }
        tvName.text = friendData.nickname
        tvMusic.text = friendData.music
    }
}