package org.sopt.dosopttemplate.util

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import org.sopt.dosopttemplate.data.Profile

fun <T> Intent.getParcelable(name: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, clazz)
    } else
        getParcelableExtra(name)
}


fun logProfile(profile: Profile) {
    Log.v("nick name", profile.name.toString())
    Log.v("mbti", profile.MBTI.toString())
    Log.v("music title", profile.getMusic())
    Log.v("intro", profile.intro.toString())
}

fun hideKeyboard(activity: Activity) {
    val imm: InputMethodManager =
        activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
}