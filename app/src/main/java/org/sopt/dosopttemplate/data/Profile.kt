package org.sopt.dosopttemplate.data

import androidx.annotation.DrawableRes

data class Profile(
    @DrawableRes val profileImage: Int,
    val name: String,
    val musicTitle: String,
    val musicArtist: String,
    val type: String
){
    val music: String = "$musicTitle: $musicArtist"
}