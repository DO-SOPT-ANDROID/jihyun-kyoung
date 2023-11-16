package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.API.RequestSignUpDto
import org.sopt.dosopttemplate.API.ServicePool.authService
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.ToastMaker.makeToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        clickSignUpBtn()
        signUp()
    }

    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            if (viewModel.isConditionSatisfied())
                processSignUp()
            else {
                val errorString = "please check for " + viewModel.getInvalidFormatField()
                makeToast(this, errorString)
            }
        }
    }

    private fun signUp() {
        binding.btSignUp.setOnClickListener() {
            val id = binding.etId.text.toString()
            val password = binding.etPw.text.toString()
            val nickname = binding.etNickName.text.toString()

            authService.signUp(RequestSignUpDto(id, nickname, password))
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        if (response.isSuccessful) {
                            processSignUp()
                        } else {
                            makeToast(this@SingUpActivity, getString(R.string.signUpFail))
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        makeToast(this@SingUpActivity, getString(R.string.serverError))
                    }

                })

        }
    }

    private fun processSignUp() {
        makeToast(this, "회원가입 완료!")
        setResult(RESULT_OK, intent)
        finish()
//      TODO:  saveSignUpData()
    }

    companion object {
        const val SIGNUPINFO = "sign up info"
    }


}
