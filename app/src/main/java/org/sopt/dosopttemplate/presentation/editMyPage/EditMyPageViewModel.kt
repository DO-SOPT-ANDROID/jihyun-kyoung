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

    val tmpString: String = "hello"
    fun getNewProfile(myProfile: Profile): Profile {
    //todo: check if value is empty
        myProfile.MBTI = mbti.value.toString()
        myProfile.musicTitle = musicTitle.value.toString()
        myProfile.name = nickName.toString()
        myProfile.musicArtist = musicArtist.value.toString()
        myProfile.intro = intro.toString()
        myProfile.setMusic()
        return myProfile
    }

    companion object {
        const val NEWPROFILE = "new profile"
    }
}