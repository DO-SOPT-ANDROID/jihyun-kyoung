package org.sopt.dosopttemplate.presentation.editMyPage

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ActivityEditmypageBinding
import org.sopt.dosopttemplate.presentation.home.HomeViewModel

class EditMyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditmypageBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val viewModel by viewModels<EditMyPageViewModel>()
    private lateinit var newProfile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editmypage)
        binding.profile = homeViewModel.getProfile(0)
        binding.lifecycleOwner = this
        newProfile = homeViewModel.getProfile(0)
        binding.viewModel = viewModel

    }

    private fun finishButtonSetOnClickListener() {
        binding.btFinish.setOnClickListener() {
            getNewProfile()
            setNewProfile()
        }
    }

    private fun getNewProfile() {
        newProfile = viewModel.getNewProfile(newProfile)
    }

    private fun setNewProfile() {
        homeViewModel.setNewProfile(newProfile, 0)
    }


}