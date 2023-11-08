package org.sopt.dosopttemplate.presentation.editMyPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.util.logProfile

class EditMyPageViewModel : ViewModel() {
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val musicTitle: MutableLiveData<String> = MutableLiveData()
    val musicArtist: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()

    val tmpString: String = "hello"
    fun setNewProfile(myProfile: Profile): Profile {
        if(!(mbti.value.isNullOrEmpty()))
            myProfile.MBTI = mbti.value.toString()
        if(!(musicTitle.value.isNullOrEmpty()))
            myProfile.musicTitle = musicTitle.value.toString()
        if(!(nickName.value.isNullOrEmpty()))
            myProfile.name = nickName.toString()
        if(!(musicArtist.value.isNullOrEmpty()))
            myProfile.musicArtist = musicArtist.value.toString()
        if(!(intro.value.isNullOrEmpty()))
            myProfile.intro = intro.value.toString()
        myProfile.setMusic()
        logProfile(myProfile)
        return myProfile
    }

    companion object {
        const val NEWPROFILE = "new profile"
    }
}