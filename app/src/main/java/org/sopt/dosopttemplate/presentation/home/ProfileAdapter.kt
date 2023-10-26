package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyprofileBinding

class ProfileAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var profileList: List<Profile> = emptyList()
    private lateinit var itemFriendBinding: ItemFriendBinding
    private lateinit var itemMyprofileBinding: ItemMyprofileBinding
    private val MY_PROFILE = 0
    private val FRIEND_PROFILE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var myView = setViewHolder(parent, viewType)
        return myView
    }

    private fun setViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MY_PROFILE -> {
                setMyProfileViewHolder(parent)
            }
            FRIEND_PROFILE -> {
                setFriendViewHolder(parent)
            }
            else -> {
                throw RuntimeException("알 수 없는 view type error")
            }
        }
    }

    private fun setFriendViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        itemFriendBinding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(itemFriendBinding)
    }

    private fun setMyProfileViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        itemMyprofileBinding = ItemMyprofileBinding.inflate(inflater, parent, false)
        return MyProfileViewHolder(itemMyprofileBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (profileList[position].type == "me") MY_PROFILE
        else FRIEND_PROFILE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendViewHolder)
            holder.onBind(profileList[position])
        else if (holder is MyProfileViewHolder)
            holder.onBind(profileList[0])
    }

    override fun getItemCount() = profileList.size

    fun setProfileList(profileList: List<Profile>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }
}

