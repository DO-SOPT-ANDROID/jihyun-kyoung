package org.sopt.dosopttemplate.presentation.editMyPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.domain.model.MBTI
import org.sopt.dosopttemplate.domain.model.Music
import org.sopt.dosopttemplate.domain.model.Profile

class EditMyPageViewModel : ViewModel() {
    private lateinit var profile: Profile
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val musicTitle: MutableLiveData<String> = MutableLiveData()
    val musicArtist: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    val id: MutableLiveData<String> = MutableLiveData()

    fun setNewProfile(): Profile {
        if (!(mbti.value.isNullOrEmpty())) profile = profile.copy(MBTI = MBTI.getByType(mbti.value.toString()))
        if (!(musicTitle.value.isNullOrEmpty()) && !(musicArtist.value.isNullOrEmpty()))
            profile = profile.copy(musicTitle = musicTitle.value.toString(), musicArtist = musicArtist.value.toString())
        if (!(intro.value.isNullOrEmpty())) profile = profile.copy(intro = intro.value.toString())

        return profile
    }
    fun setProfile(profile: Profile) {
        this.profile = profile
    }

    fun initPage() {
        id.value = profile.id.toString()
        nickName.value = profile.nickname
    }

    companion object {
        const val NEWPROFILE = "new profile"
    }
}