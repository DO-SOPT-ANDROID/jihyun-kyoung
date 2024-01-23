package org.sopt.dosopttemplate.presentation.editMyPage

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.databinding.ActivityEditmypageBinding
import org.sopt.dosopttemplate.presentation.home.HomeViewModel

class EditMyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditmypageBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val editMyPageViewModel by viewModels<EditMyPageViewModel>()
    private lateinit var myProfile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editmypage)
        binding.lifecycleOwner = this
        myProfile = homeViewModel.getProfile(0)
        binding.viewModel = editMyPageViewModel
        editMyPageViewModel.setProfile(myProfile)
        editMyPageViewModel.initPage()
        clickFinishButton()
    }

    private fun clickFinishButton() {
        binding.btFinish.setOnClickListener() {
            setNewProfile()
            getNewProfileAndParseIntent()
        }
    }

    private fun setNewProfile() {
        myProfile = editMyPageViewModel.setNewProfile()
    }

    private fun getNewProfileAndParseIntent() {
        with(intent) {
            putExtra(EditMyPageViewModel.NEWPROFILE, myProfile)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

}