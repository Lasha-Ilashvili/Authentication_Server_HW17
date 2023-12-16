package com.example.authentication_server_hw17.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentRegisterBinding
import com.example.authentication_server_hw17.view_model.RegisterViewModel
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun setListeners() {
        binding.btnRegister.setOnClickListener {
            registerViewModel.register(
                binding.etRegisterEmail.text.toString(),
                binding.etRegisterPassword.text.toString()
            )

        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.registerResult.collect {
                    if (it != null) {
                        Log.d("TestingRegister", it.toString())
                        findNavController().navigate(
                            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        )
                    } else {

                    }
                }
            }
        }
    }
}