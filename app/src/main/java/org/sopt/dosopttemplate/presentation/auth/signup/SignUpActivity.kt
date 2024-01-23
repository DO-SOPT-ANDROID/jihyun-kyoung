package org.sopt.dosopttemplate.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.presentation.auth.AuthViewModel
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.UtilClass.makeToast

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val authViewModel by viewModels<AuthViewModel>()
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel
        binding.authViewModel = authViewModel
        observeSignUpResult()
        observeLoginCondition()
    }

    private fun processSignUp() {
        makeToast(this, getString(R.string.signUpSuccess))
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun observeSignUpResult() {
        authViewModel.signUpSuccess.observe(this) {
            if (it) {
                processSignUp()
            } else {
                makeToast(this, getString(R.string.signUpFail))
            }
        }
    }

    private fun observeLoginCondition() {
        authViewModel.idConditionSatisfied.observe(this) {
            if(it == false && authViewModel.id.isNotBlank()){
                binding.tilId.error = "영문, 숫자가 포함된 6~10글자를 입력해주세요."
            }
            else {
                binding.tilId.error = null
            }
        }
        authViewModel.passwordConditionSatisfied.observe(this) {
            if (it == false && authViewModel.id.isNotBlank()) {
                binding.tilPassword.error = "영문, 숫자, 특수문자가 포함된 6~12글자를 입력해주세요."
            } else {
                binding.tilPassword.error = null
            }
        }
    }


}
