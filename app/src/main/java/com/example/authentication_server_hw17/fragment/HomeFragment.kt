package com.example.authentication_server_hw17.fragment

import androidx.navigation.fragment.findNavController
import com.example.authentication_server_hw17.base.BaseFragment
import com.example.authentication_server_hw17.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun setListeners() {
        binding.btnLogout.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            )
        }
    }

}