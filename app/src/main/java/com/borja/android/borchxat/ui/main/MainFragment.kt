package com.borja.android.borchxat.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.borja.android.borchxat.R
import com.borja.android.borchxat.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.btnChat.setOnClickListener {
            if (!binding.tiedName.text.isNullOrEmpty()) {
                viewModel.saveNickName(binding.tiedName.text.toString())
                findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
            }
        }
        subscribeToState()
        return binding.root
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewModel.uiState.collect{state ->
                when(state){
                    MainViewStates.LOADING -> {
                        binding.pbLoading.isVisible = true
                    }
                    MainViewStates.REGISTERRED -> {
                        findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
                    }
                    MainViewStates.UNREGISTERRED -> {
                        binding.pbLoading.isVisible = false
                    }
                }
            }
        }
    }
}