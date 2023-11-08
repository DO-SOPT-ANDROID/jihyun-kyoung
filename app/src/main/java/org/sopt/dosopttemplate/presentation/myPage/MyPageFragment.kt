package org.sopt.dosopttemplate.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.presentation.editMyPage.EditMyPageActivity
import org.sopt.dosopttemplate.presentation.editMyPage.EditMyPageViewModel
import org.sopt.dosopttemplate.presentation.home.HomeViewModel
import org.sopt.dosopttemplate.presentation.myPage.MyPageViewModel
import org.sopt.dosopttemplate.util.binding.BindingFragment
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.util.logProfile

class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    private lateinit var profile: Profile
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val myPageViewModel by viewModels<MyPageViewModel>()
    private val viewModel by viewModels<HomeViewModel>()
    fun newInstance(): MyPageFragment {
        val args = Bundle()
        val fragment = MyPageFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = myPageViewModel
        binding.lifecycleOwner = this
        setProfile()
        myPageViewModel.setNewProfileAndSetPage(profile)
        clickFABEdit()
        setNewProfile()
    }

    private fun setProfile() {
        profile = viewModel.getProfile(0)
    }

    private fun clickFABEdit() {
        binding.FABEdit.setOnClickListener() {
            val intent = Intent(this.context, EditMyPageActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setNewProfile() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                profile =
                    result.data?.getParcelable(EditMyPageViewModel.NEWPROFILE, Profile::class.java)
                        ?: return@registerForActivityResult
//                logProfile(profile)
                myPageViewModel.setNewProfileAndSetPage(profile)
            }
        }
    }
}