package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.data.SignUpInfo

class LogInViewModel : ViewModel() {
    private lateinit var _profile: Profile
    val profile: Profile
        get() = requireNotNull(_profile) { "profile is not initialized" }
    lateinit var _signUpInfo: SignUpInfo
    val signUpInfo: SignUpInfo
        get() = requireNotNull(_signUpInfo) { "signUp info not initialized" }

    fun isLoginAuthorized(enteredPassword: String, enteredID: String): Boolean {
        if (signUpInfo != null && profile != null)
            return signUpInfo.password == enteredPassword && profile.id ?: "" == enteredID
        return false
    }
}