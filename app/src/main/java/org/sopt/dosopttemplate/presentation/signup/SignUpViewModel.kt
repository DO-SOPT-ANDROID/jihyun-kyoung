package org.sopt.dosopttemplate.presentation.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.data.SignUpInfo

class SignUpViewModel : ViewModel() {
    lateinit var signUpInfo: SignUpInfo
    lateinit var profile: Profile
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val nickName: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    //todo make music, artist validation
    val music: MutableLiveData<String> = MutableLiveData()
    val artist: MutableLiveData<String> = MutableLiveData()
    private final val MAX = 20

    fun isConditionSatisfied(): Boolean {
        return isIDFormatValid()
                && isPasswordFormatValid()
                && isNickNameFormatValid()
                && isMbtiFormatValid()
                && isIntroFormatValid()
                && isMusicFormatValid()
    }

    private fun isIDFormatValid(): Boolean = checkStringLengthOf(id.value, 6, 10)
    private fun isPasswordFormatValid(): Boolean = checkStringLengthOf(password.value, 8, 12)
    private fun isNickNameFormatValid(): Boolean =
        checkStringLengthOf(nickName.value, 1, MAX) && isNotEmpty(nickName.value)

    private fun isMbtiFormatValid(): Boolean =
        checkStringLengthOf(mbti.value, 4, 4) && isAlphabet(mbti.value.toString())

    private fun isIntroFormatValid(): Boolean = checkStringLengthOf(intro.value, 1, MAX)

    private fun isMusicFormatValid(): Boolean = checkStringLengthOf(music.value, 1, MAX)

    private fun checkStringLengthOf(str: String?, min: Int, max: Int): Boolean =
        str?.length in min..max

    private fun isNotEmpty(str: String?): Boolean = str != " "
    private fun isAlphabet(string: String): Boolean {
        var lengthOfAlphabet = string.filter { it in 'A'..'Z' || it in 'a'..'z' }.length
        return (lengthOfAlphabet == string.length)
    }

    fun createSignUpInfo(): SignUpInfo {
        profile = Profile(toString(nickName), ME, toString(mbti), toString(intro))
        signUpInfo = SignUpInfo(toString(password), toString(id), profile)
        Log.v("test", signUpInfo.password)
        return signUpInfo
    }

    private fun toString(str: MutableLiveData<String>): String = str.value.toString()

    companion object {
        const val ME = 1
        const val FRIEND = 0
    }

    fun getInvalidFormatField(): String {
        if (!isIDFormatValid()) return "ID"
        if (!isPasswordFormatValid()) return "password"
        if (!isNickNameFormatValid()) return "nick name"
        if (!isMbtiFormatValid()) return "mbti"
        if (!isIntroFormatValid()) return "intro"
        return " "
    }
}