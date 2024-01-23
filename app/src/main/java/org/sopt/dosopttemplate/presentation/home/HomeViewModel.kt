package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.api.ServicePool
import org.sopt.dosopttemplate.data.model.toProfile
import org.sopt.dosopttemplate.domain.model.Profile

sealed class GetFollowerState {
    object Loading : GetFollowerState()
    data class Success(val data: List<Profile>) : GetFollowerState()
    object Error : GetFollowerState()
}

class HomeViewModel : ViewModel() {
    private val _getFollowerState: MutableStateFlow<GetFollowerState> =
        MutableStateFlow(GetFollowerState.Loading)
    private val getFollowerState: MutableStateFlow<GetFollowerState> get() = _getFollowerState

    val mockProfileList = mutableListOf<Profile>(
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

    fun setNewProfile(newProfile: Profile, index: Int) {
        mockProfileList[index] = newProfile
    }

//    fun getProfile(index: Int): Profile = mockProfileList[index]
//    fun getMockProfileLIst() = mockProfileList

    //    fun getResponseDataList(): MutableList<Profile> = _responseDataList
//    fun initResponseDataList(responseDataList: List<Follower>?) {
//        if (responseDataList.isNullOrEmpty()) return
//        for (i in 0..responseDataList.lastIndex) {
//            if ((i + 1) > mockProfileList.lastIndex)
//                addNewProfile(convertDataToProfile(responseDataList[i]))
//            setNewProfile(convertDataToProfile(responseDataList[i]), i + 1)
//        }
//    }

//    private fun addNewProfile(profile: Profile) {
//        mockProfileList.add(profile)
//    }

//    private fun convertDataToProfile(data: Follower): Profile {
//        val fullName = data.firstName + " " + data.lastName
//        return Profile(id = fullName, data.email, data.avatar, data.id)
//    }

    fun getAndSetFollowerList() {
        viewModelScope.launch {
            kotlin.runCatching {
                ServicePool.followerService.getFollowerList()
            }.onSuccess {
                _getFollowerState.value = GetFollowerState.Success(mockProfileList +
                        (it.body()?.data?.map { it ->
                            it.toProfile()
                        } ?: emptyList())
                )
            }.onFailure {
                _getFollowerState.value = GetFollowerState.Error
            }
        }

    }
}


