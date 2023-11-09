package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Profile) = with(binding) {
        ivProfile.setImageResource(friendData.profileImage)
        tvName.text = friendData.name
        tvMusic.text = friendData.music
    }
}