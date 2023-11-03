package org.sopt.dosopttemplate.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.R

@Parcelize
data class Profile(
    @DrawableRes
    val profileImage: Int,
    var name: String,
    var musicTitle: String?,
    var musicArtist: String?,
    val type: Int,
    var MBTI: String?,
    var intro: String?,
    val id: String?
) : Parcelable {
    var music: Music? = setMusic()

    constructor(
        @DrawableRes profileImage: Int,
        name: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        null,
        null,
        null
    )

    constructor(
        name: String,
        type: Int,
        MBTI: String,
        intro: String
    ) : this(
        R.drawable.img_noritake,
        name,
        null,
        null,
        type,
        MBTI,
        intro,
        null
    )

    constructor(
        profileImage: Int,
        name: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
        MBTI: String,
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        MBTI,
        null,
        null
    )

    fun isContainMusic(): Boolean = (music != null)
    fun getMusic(): String = if (isContainMusic()) music!!.string else " "

    fun setMusic(): Music? {
        var music: Music? = null
        if (musicArtist != null && musicTitle != null)
            music = Music(musicTitle!!, musicArtist!!)
        return music
    }
}
