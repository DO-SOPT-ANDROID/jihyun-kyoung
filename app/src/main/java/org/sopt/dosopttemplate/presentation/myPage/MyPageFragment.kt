package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.presentation.home.HomeViewModel
import org.sopt.dosopttemplate.util.binding.BindingFragment

class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    fun newInstance(): MyPageFragment {
        val args = Bundle()
        val fragment = MyPageFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<HomeViewModel>()
        val profile = viewModel.mockProfileList[0]
        binding.tvNickName.text = profile.name
        binding.tvID.text = profile.id
        binding.tvMbti.text = profile.MBTI
        binding.tvIntro.text = profile.music

    }

}