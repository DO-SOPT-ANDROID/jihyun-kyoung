package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    fun newInstance(): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileAdapter = ProfileAdapter(requireContext())
        binding.lifecycleOwner = this
        binding.rvProfiles.adapter = profileAdapter
        profileAdapter.setProfileList(viewModel.getMockProfileLIst())
    }
}