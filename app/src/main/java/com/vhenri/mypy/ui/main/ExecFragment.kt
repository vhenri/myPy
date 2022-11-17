package com.vhenri.mypy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vhenri.mypy.databinding.FragmentExecBinding
import javax.inject.Inject

class ExecFragment : Fragment() {
    lateinit var binding: FragmentExecBinding
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        viewModel = (requireActivity() as MainActivity).viewModel
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExecBinding.inflate(inflater, container, false)
        return binding.root
    }
}
