package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder (private val binding:ItemFriendBinding):
    RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendData: Profile) {
            binding.ivProfile.setImageResource(friendData.profileImage)
            binding.tvName.text = friendData.name
            binding.tvMusic.text = friendData.music
        }
}