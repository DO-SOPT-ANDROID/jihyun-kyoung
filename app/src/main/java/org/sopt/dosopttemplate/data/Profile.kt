package org.sopt.dosopttemplate.data

import androidx.annotation.DrawableRes

data class Profile(
    @DrawableRes val profileImage: Int,
    val name: String,
    val musicTitle: String,
    val musicArtist: String,
    val type: String,
    val MBTI: String?,
    val id: String?
){
    val music: String = "🎧$musicTitle - $musicArtist"
    constructor(
        @DrawableRes  profileImage: Int,
         name: String,
         musicTitle: String,
         musicArtist: String,
         type: String,
    ) : this(profileImage, name, musicTitle, musicArtist, type, null, null)

}
