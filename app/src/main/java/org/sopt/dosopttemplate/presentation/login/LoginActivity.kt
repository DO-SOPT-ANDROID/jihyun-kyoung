package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.api.AuthViewModel
import org.sopt.dosopttemplate.api.ResponseLoginDto
import org.sopt.dosopttemplate.data.SignUpInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SingUpActivity
import org.sopt.dosopttemplate.util.UtilClass.makeToast
import org.sopt.dosopttemplate.util.getParcelable
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpInfo: SignUpInfo
    private val viewModel by viewModels<LogInViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.authViewModel = authViewModel
        getSignUpInfo()
        login()
        clickSignUpBtn()
        observeLoginResult()
        observeLoginText()
    }

    private fun getSignUpInfo() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                signUpInfo =
                    result.data?.getParcelable(LogInViewModel.SIGNUPINFO, SignUpInfo::class.java)
                        ?: return@registerForActivityResult
            }
        }
    }

    private fun login() {

        binding.btLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()

            authViewModel.login(
//                id = id,
//                password = password,
            )

//            authService.login(RequestLoginDto(id, password))
//                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
//                    override fun onResponse(
//                        call: Call<ResponseLoginDto>,
//                        response: Response<ResponseLoginDto>,
//                    ) {
//                        if (response.isSuccessful) {
//                            processLogin(response)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
//                        makeToast(this@LoginActivity, getString(R.string.serverError))
//                    }
//                })
        }
    }

    private fun processLogin(response: Response<ResponseLoginDto>) {
        val data: ResponseLoginDto =
            requireNotNull(response.body()) { "Response body should not be null" }
        val userId = data.id
        makeToast(
            this@LoginActivity,
            getString(R.string.loginSuccess) + userId + getString(R.string.end)
        )
        goToMainPage()
    }

    private fun goToMainPage() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            val intent = Intent(this, SingUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun observeLoginResult() {
        authViewModel.loginSuccess.observe(this) {
            // 여기서 it은 loginSucess 객체의 value입니다.
            if (it) {
                makeToast(
                    this@LoginActivity, "로그인 성공"
                )
                goToMainPage()
            } else {
                makeToast(
                    this@LoginActivity,
                    "로그인 실패"
                )
            }
        }
    }

    private fun observeLoginText() {
        authViewModel.idConditionSatisfied.observe(this) {
            if (it == false) {
                binding.tilId.error = "영문, 숫자가 포함된 6~10글자를 입력해주세요."
            } else {
                binding.tilId.error = null
            }

        }
        authViewModel.passwordConditionSatisfied.observe(this) {
            if (it == false) {
                binding.tilPassword.error = "영문, 숫자, 특수문자가 포함된 6~12글자를 입력해주세요."
            } else {
                binding.tilPassword.error = null
            }

        }
    }


}

