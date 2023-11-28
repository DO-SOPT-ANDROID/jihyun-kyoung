package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.api.AuthViewModel
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.UtilClass.makeToast

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = signUpViewModel
        observeSignUpResult()
        signUp()
    }

    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            if (signUpViewModel.isConditionSatisfied())
                processSignUp()
            else {
                val errorString = "please check for " + signUpViewModel.getInvalidFormatField()
                makeToast(this, errorString)
            }
        }
    }

    private fun signUp() {
        binding.btSignUp.setOnClickListener() {
            val id = binding.etId.text.toString()
            val password = binding.etPw.text.toString()
            val nickname = binding.etNickName.text.toString()
            authViewModel.signUp(id, nickname, password)

        }
    }

    private fun processSignUp() {
        makeToast(this, "회원가입 완료!")
        setResult(RESULT_OK, intent)
        finish()
//      TODO:  saveSignUpData()
    }

    private fun observeSignUpResult() {
        authViewModel.signUpSuccess.observe(this) {
            if (it) {
                processSignUp()
            } else {
                val errorString = "please check for " + signUpViewModel.getInvalidFormatField()
                makeToast(this, errorString)
            }
        }
    }

    companion object {
        const val SIGNUPINFO = "sign up info"
    }


}
