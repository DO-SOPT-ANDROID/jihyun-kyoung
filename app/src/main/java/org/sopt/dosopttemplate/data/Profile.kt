package org.sopt.dosopttemplate.data

import androidx.annotation.DrawableRes

data class Profile(
    @DrawableRes private val profileImage: Int,
    private val name: String,
    private val musicTitle: String,
    private val musicArtist: String,
    private val type: String,
    private val MBTI: String?,
    private val id: String?
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
