package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpInfo(
    var password: String?,
    var profile: Profile
) : Parcelable{
    constructor(): this(null, Profile())
}