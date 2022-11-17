package com.vhenri.mypy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vhenri.mypy.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    fun initObservers(){
        binding.startButton.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.welcomeFragmentToExecFragment())
        }
    }
}