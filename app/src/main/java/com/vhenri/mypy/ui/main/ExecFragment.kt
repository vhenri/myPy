package com.vhenri.mypy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.vhenri.mypy.databinding.FragmentExecBinding
import javax.inject.Inject
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.executeCode()
        initObservables()
    }

    private fun initObservables(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch() {
                    viewModel.uiState.collect {
                        if(it.commandString.isNullOrEmpty()){
                           clearInputBox()
                        }
                        if(!it.executedCommandString.isNullOrBlank()){
                            binding.inputText.text = it.executedCommandString
                        }
                        if(!it.executedResponse.isNullOrBlank()){
                            binding.outputText.text = it.executedResponse
                        }
                    }
                }
                launch(){
                    viewModel.isLoading.collect{ isLoading ->
                        binding.execButton.isClickable = !isLoading
                        if (isLoading){
                            binding.execButton.text = "LOADING ..."
                        } else {
                            binding.execButton.text = "EXECUTE"
                        }
                    }
                }
                launch(){
                    viewModel.navigation.collect {
                        if (it != null) {
                            findNavController().navigate(it)
                        }
                    }
                }
            }
        }
        binding.execButton.setOnClickListener {
            viewModel.executeCode()
        }
    }

    fun clearInputBox(){
        binding.inputCodeInner.text?.clear()
    }

}
