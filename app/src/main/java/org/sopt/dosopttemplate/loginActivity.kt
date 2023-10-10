package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityMainBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var id:String
    private lateinit var pw:String
    private lateinit var mbti:String
    private lateinit var nickName:String
    private lateinit var intro:String
    lateinit var resultLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btLogin.setOnClickListener() {
            loginAttempt()
        }
        getSignUpData()
        binding.btSingUp.setOnClickListener(){
            signUp()
        }
    }
    private fun loginAttempt(){
        if(loginAuthorized()) {
            makeToast("로그인 성공!")
            goToMainPage()
        }
        else
            makeToast("ID 또는 비밀번호가 일치하지 않습니다.")
    }
    private fun goToMainPage(){
        val intent = Intent(this, MainPageActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("mbti", mbti)
        intent.putExtra("nickName", nickName)
        intent.putExtra("intro", intro)
        startActivity(intent)
    }
    private fun loginAuthorized():Boolean{
        var enteredID = binding.etId.text.toString()
        var enteredPW = binding.etPassword.text.toString()
        return (enteredID == id && enteredPW == pw)
    }
    private fun getSignUpData(){
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                nickName = result.data?.getStringExtra("nickName") ?: ""
                mbti = result.data?.getStringExtra("mbti") ?: ""
                intro = result.data?.getStringExtra("intro") ?: ""
            }
        }
    }
    private fun makeToast(msg:String){
        Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
    private fun signUp(){
        val intent = Intent(this, SingUpActivity::class.java)
        resultLauncher.launch(intent)
    }
}