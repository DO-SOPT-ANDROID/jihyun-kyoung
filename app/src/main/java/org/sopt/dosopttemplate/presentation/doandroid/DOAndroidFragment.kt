package org.sopt.dosopttemplate.presentation.doandroid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentDoandroidBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class DOAndroidFragment : BindingFragment<FragmentDoandroidBinding>(R.layout.fragment_doandroid) {
    val viewModel by viewModels<DOAndroidViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}