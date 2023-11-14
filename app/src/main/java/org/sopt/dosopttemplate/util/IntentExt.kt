package org.sopt.dosopttemplate.util

import android.content.Intent
import android.os.Build
import android.util.Log
import org.sopt.dosopttemplate.data.Profile

fun <T> Intent.getParcelable(name: String, clazz: Class<T>): T? {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, clazz)
    }
    else
        getParcelableExtra(name)
}