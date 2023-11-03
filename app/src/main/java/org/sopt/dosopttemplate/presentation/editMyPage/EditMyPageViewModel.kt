package org.sopt.dosopttemplate.presentation.editMyPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile

class EditMyPageViewModel : ViewModel() {
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val musicTitle: MutableLiveData<String> = MutableLiveData()
    val musicArtist: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()

    fun getNewProfile(profile: Profile): Profile {
        profile.MBTI = mbti.toString()
        profile.musicTitle = musicTitle.toString()
        profile.name = nickName.toString()
        profile.musicArtist = musicArtist.toString()
        profile.intro = intro.toString()
        profile.setMusic()

        return profile
    }

}