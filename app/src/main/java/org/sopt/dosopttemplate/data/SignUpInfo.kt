package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpInfo(
    var password: String,
    var id: String,
    var profile: Profile
) : Parcelable