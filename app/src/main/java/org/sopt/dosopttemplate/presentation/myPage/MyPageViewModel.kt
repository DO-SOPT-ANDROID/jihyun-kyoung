package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.domain.model.Profile

class MyPageViewModel : ViewModel() {
    private lateinit var profile: Profile

    //todo make my page view model
    lateinit var id: String
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val music: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    private fun setMyPage() {
        id = profile.id.toString()
        nickName.value = profile.nickname
        mbti.value = profile.MBTI.toString()
        music.value = profile.music
        intro.value = profile.intro
    }

    fun setNewProfileAndSetPage(newProfile: Profile) {
        profile = newProfile
        setMyPage()
    }

}