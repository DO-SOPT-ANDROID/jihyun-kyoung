package org.sopt.dosopttemplate.presentation.editMyPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile

class EditMyPageViewModel : ViewModel() {
    private lateinit var profile: Profile
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val musicTitle: MutableLiveData<String> = MutableLiveData()
    val musicArtist: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    val id: MutableLiveData<String> = MutableLiveData()

    fun setNewProfile(): Profile {
        if (!(mbti.value.isNullOrEmpty())) profile.MBTI = mbti.value.toString()
        if (!(musicTitle.value.isNullOrEmpty())) profile.musicTitle = musicTitle.value.toString()
        if (!(musicArtist.value.isNullOrEmpty())) profile.musicArtist = musicArtist.value.toString()
        if (!(intro.value.isNullOrEmpty())) profile.intro = intro.value.toString()
        profile.setMusic()
//        logProfile(profile)
        return profile
    }

    fun setProfile(profile: Profile) {
        this.profile = profile
    }

    fun initPage() {
        id.value = profile.id.toString()
        nickName.value = profile.name
    }

    companion object {
        const val NEWPROFILE = "new profile"
    }
}