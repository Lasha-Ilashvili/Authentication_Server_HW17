package com.example.authentication_server_hw17.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentSplashBinding
import com.example.authentication_server_hw17.view_model.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun setUp() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sessionFlow.collect {
                    delay(1500)
                    if (it.isNotEmpty()) {
                        findNavController().navigate(
                            SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                                email = it
                            )
                        )
                    } else {
                        findNavController().navigate(
                            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                        )
                    }
                }
            }
        }
    }
}