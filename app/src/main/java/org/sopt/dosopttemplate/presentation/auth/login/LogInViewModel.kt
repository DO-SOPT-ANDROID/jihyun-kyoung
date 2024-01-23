package org.sopt.dosopttemplate.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    companion object {
        const val SIGNUPINFO = "sign up info"
    }
}