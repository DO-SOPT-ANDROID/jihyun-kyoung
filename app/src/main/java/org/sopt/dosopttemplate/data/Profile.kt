package org.sopt.dosopttemplate.data

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
data class subInfo(
    var music: Music?,
    var MBTI: String,
    var intro: String,
) : Parcelable {
    fun getMusic(): String = music?.string ?: "no music"

    constructor(music: Music?) : this(music, "", "")
}

@Parcelize
data class Profile(
    @DrawableRes
    val profileImage: Int?,
    val id: String?,
    var nickname: String,
//    var music: Music?,
    val type: Int,
//    var MBTI: String?,
//    var intro: String?,
    val reqresData: ReqresData?,
    val subInfo: subInfo
) : Parcelable {
    fun getMusic(): String = subInfo.getMusic()
    fun setMusic(musicTitle: String, musicArtist: String) {
        subInfo.music = Music(musicTitle, musicArtist)
    }

    fun getMbti(): String = subInfo.MBTI
    fun setMbti(mbti: String) {
        subInfo.MBTI = mbti
    }

    fun getIntro(): String = subInfo.intro
    fun setIntro(intro: String) {
        subInfo.intro = intro
    }

    constructor(
        nickname: String,
        type: Int,
        mbti: String,
        intro: String
    ) : this(
        R.drawable.img_monkey,
        null,
        nickname,
//        null,
        type,
        null,
        subInfo(null, mbti, intro)
    )

    constructor(
        profileImage: Int,
        id : String,
        nickname: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
        mbti: String,
        intro: String,
    ) : this(
        profileImage,
        id,
        nickname,
//        Music(musicTitle, musicArtist),
        type,
//        MBTI,
//        intro,
        null,
        subInfo(Music(musicTitle, musicArtist), mbti, intro)
    )

    constructor(
        nickname: String,
        email: String,
        avatar: String,
        idInt: Int
    ) : this(
        null,
        null,
        nickname,
//        null,
        FRIEND,
        ReqresData(email, avatar, idInt),
        subInfo(null)
    )


    companion object {
        const val ME = 1
        const val FRIEND = 0
    }
}
