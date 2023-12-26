package com.example.authentication_server_hw17.presentation.login

import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.data.datastore.DataStore
import com.example.authentication_server_hw17.data.model.User
import com.example.authentication_server_hw17.data.resources.ResultResponse
import com.example.authentication_server_hw17.databinding.FragmentLoginBinding
import com.example.authentication_server_hw17.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
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
            loginViewModel.onEvent(LoginEvent.Login(User(email, password)))
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