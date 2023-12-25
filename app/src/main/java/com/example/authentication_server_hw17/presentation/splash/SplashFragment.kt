package com.example.authentication_server_hw17.presentation.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.databinding.FragmentSplashBinding
import com.example.authentication_server_hw17.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun setUp() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sessionFlow.collect {
                    delay(1500)

                    findNavController().navigate(
                        if (it is SplashFragmentNavigationEvent.NavigationHome)
                            SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                                email = it.email
                            )
                        else
                            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                    )
                }
            }
        }
    }
}