package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.UtilClass.makeToast
import org.sopt.dosopttemplate.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by activityViewModels<HomeViewModel>()
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
        binding.rvProfiles.adapter = profileAdapter
        viewModel.getAndSetFollower()
        initFollowersObserver()
    }

    private fun initFollowersObserver() {
        viewModel.getFollowerState.observe(viewLifecycleOwner) {
            when (it) {
                is GetFollowerState.Success -> {
                    profileAdapter.setProfileList(it.data)
                }

                is GetFollowerState.Loading -> makeToast(requireContext(), "로딩중")
                is GetFollowerState.Error -> makeToast(requireContext(), "follower를 불러오기에 실패하였습니다.")
            }
        }
    }
}