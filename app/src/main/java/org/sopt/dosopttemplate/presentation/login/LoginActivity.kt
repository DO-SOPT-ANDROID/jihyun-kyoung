package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.SignUpInfo
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SingUpActivity
import org.sopt.dosopttemplate.util.ToastMaker.makeToast
import org.sopt.dosopttemplate.util.getParcelable

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var signUpInfo: SignUpInfo
    private val viewModel by viewModels<LogInViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        getSignUpInfo()
        clickLoginBtn()
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

    private fun clickLoginBtn() {
        binding.btLogin.setOnClickListener() {

            Log.v("signUp Info", signUpInfo.id)
            if (viewModel.isLoginAuthorized(signUpInfo)) {
                makeToast(this, "로그인 성공!")
                goToMainPage()
            } else
                makeToast(this, "ID 또는 비밀번호가 일치하지 않습니다.")
        }
    }

    private fun goToMainPage() {
        val intent = Intent(this, HomeActivity::class.java)
        with(intent) {
            putExtra("id", signUpInfo.id)
        }
        startActivity(intent)
    }


    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            val intent = Intent(this, SingUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}

