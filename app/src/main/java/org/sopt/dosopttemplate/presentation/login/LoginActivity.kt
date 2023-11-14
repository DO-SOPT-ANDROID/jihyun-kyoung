package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.API.RequestLoginDto
import org.sopt.dosopttemplate.API.ResponseLoginDto
import org.sopt.dosopttemplate.API.ServicePool.authService
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.SignUpInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SingUpActivity
import org.sopt.dosopttemplate.util.ToastMaker.makeToast
import org.sopt.dosopttemplate.util.getParcelable
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpInfo: SignUpInfo
    private val viewModel by viewModels<LogInViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        getSignUpInfo()
        login()
        clickSignUpBtn()
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
        val id = binding.etId.text.toString()
        val password = binding.etPassword.text.toString()

        binding.btLogin.setOnClickListener {
            authService.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            processLogin(response)
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        makeToast(this@LoginActivity, getString(R.string.serverError))
                    }

                })

        }

    }

    private fun processLogin(response: Response<ResponseLoginDto>) {
        val data: ResponseLoginDto = response.body()!!
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

}

