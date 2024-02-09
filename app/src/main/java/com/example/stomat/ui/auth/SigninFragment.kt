package com.example.stomat.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stomat.App
import com.example.stomat.Const
import com.example.stomat.MainActivity
import com.example.stomat.R
import com.example.stomat.databinding.FragmentSigninBinding
import javax.inject.Inject

class SigninFragment : Fragment() {
    companion object {
        fun getBundle(authMode: Int) = Bundle().apply {
            putInt(Const.KEY_AUTH_MODE, authMode)
        }
    }

    private var authMode = 0
    lateinit var viewModel: SigninViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    @Inject
//    lateinit var apiService: ApiService

    lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authMode = arguments?.getInt(Const.KEY_AUTH_MODE) ?: 0
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SigninViewModel::class.java)
        binding = FragmentSigninBinding.inflate(inflater, container, false)

        initListeners()
        initObservers()

        initViews()

        return binding.root
    }

    private fun initViews() {
        binding.itEmail.setText("sergeykozlov.d@ya.ru")
        binding.itPassword.setText("000000")


        var title = ""
        var button = ""
        when (authMode) {
            Const.AUTH_SIGNIN -> {
                title = getString(R.string.sign_in)
                button = getString(R.string.enter)
                binding.tvForgot.visibility = View.VISIBLE
                binding.tvSignup.visibility = View.VISIBLE
                binding.tvSignin.visibility = View.GONE

            }

            Const.AUTH_RECOVERY -> {
                title = getString(R.string.password_recovery)
                button = getString(R.string.recovery)
                binding.tvForgot.visibility = View.GONE
                binding.tvSignup.visibility = View.VISIBLE
                binding.tvSignin.visibility = View.GONE
            }

            Const.AUTH_SIGNUP -> {
                title = getString(R.string.register)
                button = getString(R.string.register)
                binding.tvForgot.visibility = View.GONE
                binding.tvSignup.visibility = View.GONE
                binding.tvSignin.visibility = View.VISIBLE
            }
        }

        binding.button.text = button
        (activity as MainActivity).setTitle(title)
    }

    private fun initObservers() {
        viewModel.isDataValid.observe(viewLifecycleOwner) {
            binding.button.isEnabled = it
        }

        viewModel.codeSent.observe(viewLifecycleOwner) {
            if (it) {
                //goto code confirm
                findNavController().navigateUp()
                findNavController().navigate(
                    R.id.CodeFragment,
                    CodeFragment.getBundle(viewModel.email.value ?: "")
                )
            }
        }

        viewModel.isSignin.observe(viewLifecycleOwner){
            if (it) {
                findNavController().navigateUp()
                findNavController().navigate(R.id.ProfileFragment)
            }
        }
    }

    private fun initListeners() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(
                R.id.SigninFragment,
                getBundle(Const.AUTH_SIGNUP)
            )
        }

        binding.tvSignin.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(
                R.id.SigninFragment,
                getBundle(Const.AUTH_SIGNIN)
            )
        }

        binding.button.setOnClickListener {
            when (authMode) {
                Const.AUTH_SIGNIN -> viewModel.signin()
                Const.AUTH_SIGNUP -> viewModel.signup()
                Const.AUTH_RECOVERY -> viewModel.recovery()
            }
        }

        binding.itEmail.addTextChangedListener {
            viewModel.email.value = it.toString()
        }

        binding.itPassword.addTextChangedListener {
            viewModel.password.value = it.toString()
        }

        binding.tvForgot.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(
                R.id.SigninFragment,
                getBundle(Const.AUTH_RECOVERY)
            )
        }
    }
}