package org.sopt.dosopttemplate.presentation.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.api.FollowerService
import org.sopt.dosopttemplate.data.model.ResponseGetFollowerDto
import org.sopt.dosopttemplate.api.ServicePool
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.UtilClass.makeToast
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
        lifecycleScope.launch {
            kotlin.runCatching{
                ServicePool.followerService.getFollowerList()
            }.onSuccess {
                profileAdapter.setProfileList(it.body()?.data.to)
            }
        }
//        ServicePool.followerService.getFollowerList()
//            .enqueue(object : Callback<ResponseGetFollowerDto> {
//                override fun onResponse(
//                    call: Call<ResponseGetFollowerDto>,
//                    response: Response<ResponseGetFollowerDto>
//                ) {
//                    if (response.isSuccessful) {
//                        setProfileListFromResponse(response)
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseGetFollowerDto>, t: Throwable) {
//                    Log.v("serverError", t.toString())
//                    makeToast(context as Activity, getString(R.string.serverError))
//                }
//            })
    }

    private fun setProfileListFromResponse(response: Response<ResponseGetFollowerDto>) {
        val responseBody = response.body()
        val data = responseBody?.data
        viewModel.initResponseDataList(data)
        profileAdapter.setProfileList(viewModel.getMockProfileLIst())
    }
}