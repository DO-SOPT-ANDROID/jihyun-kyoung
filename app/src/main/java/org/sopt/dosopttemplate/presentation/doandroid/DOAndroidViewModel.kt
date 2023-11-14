package org.sopt.dosopttemplate.presentation.doandroid

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.API.ResponseData
import org.sopt.dosopttemplate.data.Profile

class DOAndroidViewModel : ViewModel() {
    private var _responseDataList: MutableList<Profile> = mutableListOf<Profile>()

    fun getResponseDataList(): MutableList<Profile> = _responseDataList
    fun initResponseDataList(responseDataList: List<ResponseData>?) {
        if (responseDataList.isNullOrEmpty()) return
        for (data in responseDataList) {
            _responseDataList.add(convertDataToProfile(data))
        }
    }

    private fun convertDataToProfile(data: ResponseData): Profile =
        Profile(getName(data), data.email, data.avatar, data.id)

    private fun getName(data: ResponseData): String = data.first_name + " " + data.last_name
}