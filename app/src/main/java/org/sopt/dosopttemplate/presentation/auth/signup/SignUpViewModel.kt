package org.sopt.dosopttemplate.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.domain.model.Profile

class SignUpViewModel : ViewModel() {
    lateinit var profile: Profile
    val mbti: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    val music: MutableLiveData<String> = MutableLiveData()
    val artist: MutableLiveData<String> = MutableLiveData()

}