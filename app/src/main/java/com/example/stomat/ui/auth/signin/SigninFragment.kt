package com.example.stomat.ui.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stomat.R
import com.example.stomat.databinding.FragmentSigninBinding

class SigninFragment:Fragment() {

    private val viewModel: SigninViewModel by viewModels()
    lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater,container,false)

        initListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers(){
        viewModel.isDataValid.observe(viewLifecycleOwner){
            binding.button.isEnabled = it
        }
    }

    private fun initListeners(){
        binding.tvSignup.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(R.id.SignupFragment)
        }

        binding.button.setOnClickListener {
            viewModel.signin()
        }

        binding.itEmail.addTextChangedListener {
            viewModel.email.value = it.toString()
        }

        binding.itPassword.addTextChangedListener {
            viewModel.password.value = it.toString()
        }
    }
}