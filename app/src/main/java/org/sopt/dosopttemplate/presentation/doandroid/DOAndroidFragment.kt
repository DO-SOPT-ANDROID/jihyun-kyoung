package org.sopt.dosopttemplate.presentation.doandroid

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.API.ResponseGetFollwerDto
import org.sopt.dosopttemplate.API.ServicePool.followerService
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentDoandroidBinding
import org.sopt.dosopttemplate.presentation.home.ProfileAdapter
import org.sopt.dosopttemplate.util.ToastMaker.makeToast
import org.sopt.dosopttemplate.util.binding.BindingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DOAndroidFragment : BindingFragment<FragmentDoandroidBinding>(R.layout.fragment_doandroid) {
    val viewModel by viewModels<DOAndroidViewModel>()
    private var _profileAdapter: ProfileAdapter? = null
    val profileAdapter: ProfileAdapter
        get() = requireNotNull(_profileAdapter) { "profileAdapter not initialized" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _profileAdapter = ProfileAdapter(requireContext())
        binding.rvProfiles.adapter = profileAdapter
        getFollowerList()
    }

    private fun getFollowerList() {
        followerService.getFollowerList()
            .enqueue(object : Callback<ResponseGetFollwerDto> {
                override fun onResponse(
                    call: Call<ResponseGetFollwerDto>,
                    response: Response<ResponseGetFollwerDto>
                ) {
                    if (response.isSuccessful) {
                        setProfileListFromResponse(response)
                    }
                }

                override fun onFailure(call: Call<ResponseGetFollwerDto>, t: Throwable) {
                    Log.v("serverError", t.toString())
                    val context = context as Activity
                    makeToast(context, getString(R.string.serverError))
                }
            })
    }

    private fun setProfileListFromResponse(response: Response<ResponseGetFollwerDto>) {
        val responseBody = response.body()
        val data = responseBody?.data
        Log.v("data", data.toString())
        viewModel.initResponseDataList(data)
        profileAdapter.setProfileList(viewModel.getResponseDataList())
    }

}