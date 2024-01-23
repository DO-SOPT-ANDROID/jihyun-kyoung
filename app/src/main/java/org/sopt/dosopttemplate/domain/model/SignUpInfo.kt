package org.sopt.dosopttemplate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpInfo(
    var password: String,
    var id: String,
    var profile: Profile
) : Parcelable