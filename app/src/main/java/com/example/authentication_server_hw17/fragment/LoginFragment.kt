package com.example.authentication_server_hw17.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentLoginBinding
import com.example.authentication_server_hw17.view_model.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun setListeners() {

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            loginViewModel.checkEmail(email)
        }

        binding.btnLoginRegister.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.isEmailRegistered.collect { state ->
                    if (state?.isRegistered == true) {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        )
                    } else {
                        // Handle email not registered
                    }
                }
            }
        }
    }
}