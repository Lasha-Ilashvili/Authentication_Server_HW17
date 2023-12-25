package com.example.authentication_server_hw17.fragment

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.Events
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentRegisterBinding
import com.example.authentication_server_hw17.model.User
import com.example.authentication_server_hw17.resources.ResultResponse
import com.example.authentication_server_hw17.view_model.RegisterViewModel
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun setListeners() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            registerViewModel.onEvent(Events.Register(User(email, password)))
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.registrationResult.collect { result ->
                    when (result) {
                        is ResultResponse.Success -> {
                            setFragmentResult(
                                "request_key_email",
                                bundleOf("bundle_key" to result.token.email)
                            )
                            setFragmentResult(
                                "request_key_password",
                                bundleOf("bundle_key" to result.token.password)
                            )
                            findNavController().navigate(
                                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
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