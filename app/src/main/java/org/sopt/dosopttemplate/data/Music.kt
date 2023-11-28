package org.sopt.dosopttemplate.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(val title: String, val artist: String) : Parcelable {
    val string: String = "🎧$title - $artist"
}