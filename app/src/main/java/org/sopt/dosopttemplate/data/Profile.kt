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
    val id: String?,
    val intro: String?
) : Parcelable {
    val music: String = "🎧$musicTitle - $musicArtist"
    constructor(
        @DrawableRes  profileImage: Int,
         name: String,
         musicTitle: String,
         musicArtist: String,
         type: Int,
    ) : this(profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        null,
        null,
        null)
    constructor(
        name: String,
        type: Int,
        MBTI: String,
        id: String,
        intro: String
    ) : this(
        R.drawable.img_noritake,
        name,
        null,
        null,
        type,
        MBTI,
        id,
        intro)

    constructor(
        profileImage: Int,
        name: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
        MBTI: String,
        id: String
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        MBTI,
        id,
        null
    )

    constructor(): this(R.drawable.img_noritake,
        "no name",
        null,
        null,
        1,
        null,
        null,
        null)
}
