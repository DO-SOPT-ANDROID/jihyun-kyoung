package org.sopt.dosopttemplate.presentation.logIn.signUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import  org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.ToastMaker.makeToast

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickSignUpBtn()
    }

    private fun clickSignUpBtn() {
        binding.btSignUp.setOnClickListener() {
            if (isConditionSatisfied()) {
                makeToast(this, "회원가입 완료!")
                sendDataToLoginActivity()
            } else
                makeToast(this, "입력값이 조건에 맞지 않습니다.")
        }
    }

    private fun sendDataToLoginActivity() {
        with(intent) {
            putExtra("id", binding.etId.text.toString())
            putExtra("pw", binding.etPw.text.toString())
            putExtra("nickName", binding.etNickName.text.toString())
            putExtra("mbti", binding.etMbti.text.toString())
            putExtra("intro", binding.etIntro.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun isConditionSatisfied(): Boolean {
        return ((binding.etId.length() in 6..10)
                && (binding.etPw.length() in 8..12)
                && (binding.etNickName.length() >= 1 && !binding.etNickName.equals(" "))
                && (binding.etMbti.length() == 4 && isAlphabet(binding.etMbti.text.toString()))
                && (binding.etIntro.length() != 0))
    }

    private fun isAlphabet(string: String): Boolean {
        var lengthOfAlphabet = string.filter { it in 'A'..'Z' || it in 'a'..'z' }.length
        return (lengthOfAlphabet == string.length)
    }
}

