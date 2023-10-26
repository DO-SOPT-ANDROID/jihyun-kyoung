//package org.sopt.dosopttemplate
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import org.sopt.dosopttemplate.databinding.ActivityMainpageBinding
//
//class MainPageActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainpageBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainpageBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        getDataFromLogin()
//    }
//
//    private fun getDataFromLogin() {
//        binding.tvID.text = intent.getStringExtra("id")
//        binding.tvMbti.text = intent.getStringExtra("mbti")
//        binding.tvNickName.text = intent.getStringExtra("nickName")
//        binding.tvIntro.text = intent.getStringExtra("intro")
//    }
//}


package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.fragments.MyPageFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.fragments.DOAndroidFragment
import org.sopt.dosopttemplate.fragments.HomeFragment

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_do_android -> {
                    replaceFragment(DOAndroidFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

}