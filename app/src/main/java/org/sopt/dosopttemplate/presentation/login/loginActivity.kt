package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Profile
import org.sopt.dosopttemplate.data.SignUpInfo
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SingUpActivity
import org.sopt.dosopttemplate.util.getParcelable

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var id: String
    private lateinit var password: String
    private lateinit var signUpInfo: SignUpInfo
    private lateinit var profile: Profile
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LogInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        getSignUpData()
        clickLoginBtn()
        clickSignUpBtn()
    }

    private fun clickLoginBtn() {
        binding.btLogin.setOnClickListener() {
            if (isLoginAuthorized()) {
                makeToast("로그인 성공!")
                goToMainPage()
            } else
                makeToast("ID 또는 비밀번호가 일치하지 않습니다.")
        }
    }

    private fun goToMainPage() {
        val intent = Intent(this, HomeActivity::class.java)
        with(intent) {
            putExtra("id", id)
        }
        startActivity(intent)
    }

    private fun isLoginAuthorized(): Boolean {
        val enteredID = binding.etId.text.toString()
        val enteredPW = binding.etPassword.text.toString()
        return (enteredID == id && enteredPW == password)
    }

    private fun getSignUpData() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                signUpInfo = result.data?.getParcelable(SIGNUPINFO, SignUpInfo::class.java)
                    ?: return@registerForActivityResult
                Log.v(SIGNUPINFO, signUpInfo.password.toString())
                profile = signUpInfo.profile
                password = signUpInfo.password ?: ""
                id = profile.id ?: ""
            }
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            val intent = Intent(this, SingUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    companion object {
        const val SIGNUPINFO = "sign up info"
    }
}

