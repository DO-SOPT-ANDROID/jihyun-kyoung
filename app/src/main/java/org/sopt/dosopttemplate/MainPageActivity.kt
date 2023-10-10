package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import  org.sopt.dosopttemplate.databinding.ActivityMainpageBinding

class MainPageActivity :AppCompatActivity(){
    private lateinit var binding: ActivityMainpageBinding
    private lateinit var intent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromLogin()
    }
    private fun getDataFromLogin(){
        intent = getIntent()
        binding.tvID.text =  intent.getStringExtra("id")
        binding.tvMbti.text = intent.getStringExtra("mbti")
        binding.tvNickName.text = intent.getStringExtra("nickName")
        binding.tvIntro.text = intent.getStringExtra("intro")
    }
}