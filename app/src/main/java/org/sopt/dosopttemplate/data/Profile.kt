package org.sopt.dosopttemplate.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.R

@Parcelize
data class Profile(
    @DrawableRes
    val profileImage: Int,
    val name: String,
    val musicTitle: String?,
    val musicArtist: String?,
    val type: Int,
    val MBTI: String?,
    val intro: String?,
    val id: String?
) : Parcelable {
    val music: Music? = (musicTitle?.let { musicArtist?.let { it1 -> Music(it, it1) } } ?:null) as Music

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
}
