package org.sopt.dosopttemplate.presentation.myPage

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile
import androidx.fragment.app.viewModels

class MyPageViewModel : ViewModel() {
    private lateinit var profile: Profile

    //todo make my page view model
//    val id: MatableLiveData<String> = MutableLiveData()
//    val nickName: MatableLiveData<String> = MutableLiveData()
//    val mbti: MatableLiveData<String> = MutableLiveData()
//    val music: MatableLiveData<String> = MutableLiveData()
//    fun setMyPage(profile: Profile) {
//        this.profile = profile
//        id = profile.id
//        nickName = profile.name
//        mbti = profile.MBTI
//        music = if (profile.isContainMusic()) profile.music.string else ""
//    }
//    fun setNewProfile(newProfile:Profile) {
//        profile = newProfile
//    }

    companion object {
        const val NEWPROFILE = "new profile"
    }
}