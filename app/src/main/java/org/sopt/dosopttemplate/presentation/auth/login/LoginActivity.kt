package org.sopt.dosopttemplate.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.presentation.auth.AuthViewModel
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.auth.signup.SingUpActivity
import org.sopt.dosopttemplate.util.UtilClass.makeToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LogInViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.authViewModel = authViewModel
        clickSignUpBtn()
        observeLoginResult()
        observeLoginText()
    }

    private fun clickSignUpBtn() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
            }
        }
        binding.btSignUp.setOnClickListener() {
            val intent = Intent(this, SingUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun observeLoginResult() {
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                makeToast(
                    this@LoginActivity, "로그인 성공"
                )
                goToMainPage()
            } else {
                makeToast(
                    this@LoginActivity, "로그인 실패"
                )
            }
        }
    }

    private fun goToMainPage() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun observeLoginText() {
        observeId()
        obsercePassword()
    }

    private fun obsercePassword() {
        authViewModel.passwordConditionSatisfied.observe(this) {
            if (it == false && authViewModel.password.isNotBlank()) {
                binding.tilPassword.error = "영문, 숫자, 특수문자가 포함된 6~12글자를 입력해주세요."
            } else {
                binding.tilPassword.error = null
            }

        }
    }

    private fun observeId() {
        authViewModel.idConditionSatisfied.observe(this) {
            if (it == false && authViewModel.id.isNotBlank()) {
                binding.tilId.error = "영문, 숫자가 포함된 6~10글자를 입력해주세요."
            } else {
                binding.tilId.error = null
            }

        }
    }
}

