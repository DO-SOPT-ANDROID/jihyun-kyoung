package org.sopt.dosopttemplate.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.api.ServicePool.reqresService
import org.sopt.dosopttemplate.data.model.ResponseGetFollowerDto
import org.sopt.dosopttemplate.domain.model.Profile

sealed class GetFollowerState {
    object Loading : GetFollowerState()
    data class Success(val data: List<Profile>) : GetFollowerState()
    object Error : GetFollowerState()
}

class HomeViewModel : ViewModel() {
    private val _getFollowerState = MutableLiveData<GetFollowerState>(GetFollowerState.Loading)
    val getFollowerState: MutableLiveData<GetFollowerState> get() = _getFollowerState

    val myProfile = mutableListOf<Profile>(
        Profile(
            profileImage = R.drawable.img_monkey,
            id = "jihyune_hi",
            nickname = "경지현",
            musicTitle = "숲",
            musicArtist = "최유리",
            type = Profile.ME,
            intro = "안녕하세요"
        )
    )
    fun setNewProfile(profile: Profile, index: Int) {
        myProfile[index] = profile
    }
    fun getAndSetFollower() {
        viewModelScope.launch {
            kotlin.runCatching {
                reqresService.getFollowerList()
            }.onSuccess {
                getFollowerState.value = GetFollowerState.Success(myProfile + it.toProfiles())
            }.onFailure {
                getFollowerState.value = GetFollowerState.Error
                Log.d("server error", it.toString())
            }
        }
    }
}


