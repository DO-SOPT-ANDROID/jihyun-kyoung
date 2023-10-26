package org.sopt.dosopttemplate.util

import android.content.Context
import android.widget.Toast

object ToastMaker {
    private var toast: Toast? = null
    fun makeToast(context: Context, msg: String) {
        toast?.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast?.show()
    }
}