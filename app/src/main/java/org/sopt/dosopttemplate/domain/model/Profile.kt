package org.sopt.dosopttemplate.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.R

@Parcelize
data class ReqresData(
    val email: String,
    val avatar: String,
    val idInt: Int
) : Parcelable

@Parcelize
data class Profile(
    @DrawableRes
    val profileImage: Int = R.drawable.img_monkey,
    val id: String?,
    val intId: Int,
    var nickname: String,
    val type: Int,
    val music: Music = Music("default music", "no artist"),
    val MBTI: MBTI = org.sopt.dosopttemplate.domain.model.MBTI.INTJ,
    val intro: String = "",
    val email: String?,
    val avatar: String?,
) : Parcelable {

    companion object {
        const val ME = 1
        const val FRIEND = 0
    }
}

enum class MBTI(val type: String) {
    ENFP("enfp"),
    ENFJ("enfj"),
    ENTJ("entj"),
    ENTP("entp"),
    ESFJ("esfj"),
    ESFP("esfp"),
    ESTJ("estj"),
    ESTP("estp"),
    INFJ("infj"),
    INFP("infp"),
    INTJ("intj"),
    INTP("intp"),
    ISFJ("isfj"),
    ISFP("isfp"),
    ISTJ("istj"),
    ISTP("istp")
}