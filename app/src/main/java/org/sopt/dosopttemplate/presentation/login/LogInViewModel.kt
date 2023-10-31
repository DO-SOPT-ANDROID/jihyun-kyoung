package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.SignUpInfo

class LogInViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    fun isLoginAuthorized(signUpInfo: SignUpInfo): Boolean =
        signUpInfo.password == password.value && signUpInfo.id == id.value


    companion object {
        const val SIGNUPINFO = "sign up info"
    }
}