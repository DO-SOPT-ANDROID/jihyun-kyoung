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
import org.sopt.dosopttemplate.presentation.home.HomeViewModel
import org.sopt.dosopttemplate.presentation.myPage.MyPageViewModel
import org.sopt.dosopttemplate.util.binding.BindingFragment

class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    private lateinit var profile: Profile
    private lateinit var resultLauncher:ActivityResultLauncher<Intent>
    private lateinit var tmpString:String
    fun newInstance(): MyPageFragment {
        val args = Bundle()
        val fragment = MyPageFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<HomeViewModel>()
        profile = viewModel.getProfile(0)
        getNewProfile()
        setMyPage()
        clickFABEdit()
    }

    private fun clickFABEdit() {
        binding.FABEdit.setOnClickListener() {
           val intent = Intent(this.context,EditMyPageActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun getNewProfile() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == RESULT_OK) {
                tmpString =
                    result.data?.getStringExtra(MyPageViewModel.NEWPROFILE).toString()
            }
        }
    }
    private fun setMyPage() {
        binding.tvID.text = profile.id
        binding.tvIntro.text = profile.intro
        binding.tvMbti.text = profile.MBTI
        binding.tvNickName.text = profile.name
        binding.tvMusic.text = profile.getMusic()
    }


}