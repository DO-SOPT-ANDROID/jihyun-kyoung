package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile

class MyPageViewModel : ViewModel() {
    private lateinit var profile: Profile

    //todo make my page view model
    lateinit var id: String
    lateinit var nickName: String
    lateinit var mbti: String
    lateinit var music: String
    lateinit var intro: String
    private fun setMyPage() {
        id = profile.id.toString()
        nickName = profile.name
        mbti = profile.MBTI.toString()
        music = profile.getMusic()
        intro = profile.intro.toString()
    }

    fun setNewProfileAndSetPage(newProfile: Profile) {
        profile = newProfile
        setMyPage()
    }

}