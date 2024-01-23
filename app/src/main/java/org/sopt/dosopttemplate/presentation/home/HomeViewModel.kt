package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.ResponseData
import org.sopt.dosopttemplate.domain.model.Profile

class HomeViewModel : ViewModel() {
    val mockProfileList = mutableListOf<Profile>(
        Profile(
            profileImage = R.drawable.img_monkey,
            id = "jihyune_hi",
            nickname = "경지현",
            musicTitle = "숲",
            musicArtist = "최유리",
            type = ME,
            mbti = "intj",
            intro = "안녕하세요"
        )
    )

    fun setNewProfile(newProfile: Profile, index: Int) {
        mockProfileList[index] = newProfile
    }

    fun getProfile(index: Int): Profile = mockProfileList[index]
    fun getMockProfileLIst() = mockProfileList

    //    fun getResponseDataList(): MutableList<Profile> = _responseDataList
    fun initResponseDataList(responseDataList: List<ResponseData>?) {
        if (responseDataList.isNullOrEmpty()) return
        for (i in 0..responseDataList.lastIndex) {
            if ((i + 1) > mockProfileList.lastIndex)
                addNewProfile(convertDataToProfile(responseDataList[i]))
            setNewProfile(convertDataToProfile(responseDataList[i]), i + 1)
        }
    }

    private fun addNewProfile(profile: Profile) {
        mockProfileList.add(profile)
    }

    private fun convertDataToProfile(data: ResponseData): Profile {
        val fullName = data.firstName + " " + data.lastName
        return Profile(fullName, data.email, data.avatar, data.id)
    }

    companion object {
        const val ME = 1
        const val FRIEND = 0
    }

}


