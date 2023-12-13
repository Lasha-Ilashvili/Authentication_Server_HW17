package com.example.authentication_server_hw17.fragment

import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun setListeners() {
        binding.btnRegister.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}