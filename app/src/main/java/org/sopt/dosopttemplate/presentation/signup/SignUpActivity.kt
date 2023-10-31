package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.home.HomeViewModel
import org.sopt.dosopttemplate.presentation.login.LogInViewModel
import org.sopt.dosopttemplate.util.ToastMaker.makeToast

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        clickSignUpBtn()
    }

    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            if (viewModel.isConditionSatisfied())
                signUp()
            else {
                val errorString = "please check for" + viewModel.getInvalidFormatField()
                makeToast(this, errorString)
            }
        }
    }

    private fun signUp() {
        makeToast(this, "회원가입 완료!")
        sendDataToLoginActivity()
//      TODO:  saveSignUpData()
    }

    private fun saveSignUpData() {
        val signUpInfo = viewModel.createSignUpInfo()
        val homeViewModel by viewModels<HomeViewModel>()
        homeViewModel.mockProfileList[0] = signUpInfo.profile ?: Profile()
        val viewModel by viewModels<LogInViewModel>()
        viewModel._signUpInfo = signUpInfo
    }

    private fun sendDataToLoginActivity() {
        with(intent) {
            val signUpData = viewModel.createSignUpInfo()
            putExtra(SIGNUPINFO, signUpData)
            Log.v(SIGNUPINFO, signUpData.password ?: "")
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val SIGNUPINFO = "sign up info"
    }


}

