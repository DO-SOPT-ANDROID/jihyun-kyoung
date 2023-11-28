package org.sopt.dosopttemplate.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

object UtilClass {
    private var toast: Toast? = null
    fun makeToast(context: Context, msg: String) {
        toast?.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun checkStringLengthOf(str: String?, min: Int, max: Int): Boolean =
        str?.length in min..max

    fun isIdConditionSatisfied(str: String): Boolean {
        val englishAndNumRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,10}\$")
        return str.matches(englishAndNumRegex)
    }

    fun isPasswordConditionSatisfied(str: String): Boolean {
        val englishAndNumSpecialRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*()-_=+]).{6,12}\$")
        return str.matches(englishAndNumSpecialRegex)
    }

}

fun getMutableDataString(str: MutableLiveData<String>): String = str.value.toString()