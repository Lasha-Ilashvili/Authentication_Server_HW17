package com.example.authentication_server_hw17.fragment

import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.Events
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentLoginBinding
import com.example.authentication_server_hw17.datastore.DataStore
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.resources.ResultResponse
import com.example.authentication_server_hw17.view_model.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun setUp() {
        setFragmentResultListener("request_key_email") { _, bundle ->
            val email = bundle.getString("bundle_key") ?: ""
            binding.etLoginEmail.setText(email)
        }

        setFragmentResultListener("request_key_password") { _, bundle ->
            val password = bundle.getString("bundle_key") ?: ""
            binding.etLoginPassword.setText(password)
        }
    }

    override fun setListeners() {

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()
            loginViewModel.onEvent(Events.Login(User(email, password)))
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
                loginViewModel.loginResult.collect { result ->
                    when (result) {
                        is ResultResponse.Success -> {
                            val email = result.token.token

                            if (binding.checkBox.isChecked) {
                                DataStore.saveEmail(email)
                            }

                            findNavController().navigate(
                                LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                    email = email
                                )
                            )
                        }

                        is ResultResponse.Error -> {
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}