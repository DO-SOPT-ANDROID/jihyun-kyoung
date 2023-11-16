package org.sopt.dosopttemplate.presentation.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.API.ResponseGetFollwerDto
import org.sopt.dosopttemplate.API.ServicePool
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.ToastMaker
import org.sopt.dosopttemplate.util.binding.BindingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var _profileAdapter: ProfileAdapter? = null
    val profileAdapter: ProfileAdapter
        get() = requireNotNull(_profileAdapter) { "profileAdapter not initialized" }

    fun newInstance(): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _profileAdapter = ProfileAdapter(requireContext())
        binding.lifecycleOwner = this
        binding.rvProfiles.adapter = profileAdapter
        getFollowerList()
    }

    private fun getFollowerList() {
        ServicePool.followerService.getFollowerList()
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
                    ToastMaker.makeToast(context, getString(R.string.serverError))
                }
            })
    }

    private fun setProfileListFromResponse(response: Response<ResponseGetFollwerDto>) {
        val responseBody = response.body()
        val data = responseBody?.data
        viewModel.initResponseDataList(data)
        profileAdapter.setProfileList(viewModel.getMockProfileLIst())
    }
}