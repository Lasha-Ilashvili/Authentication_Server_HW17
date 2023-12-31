package com.example.authentication_server_hw17.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentHomeBinding
import com.example.authentication_server_hw17.view_model.HomeFragmentNavigationEvent
import com.example.authentication_server_hw17.view_model.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val navArgs: HomeFragmentArgs by navArgs()

    private val viewModel: HomeViewModel by viewModels()

    override fun setUp() {
        binding.tvHomeEmail.text = navArgs.email
    }

    override fun setListeners() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sessionFlow.collect { completed ->
                    if (completed is HomeFragmentNavigationEvent.NavigationLogin) {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                        )
                    }
                }
            }
        }
    }
}