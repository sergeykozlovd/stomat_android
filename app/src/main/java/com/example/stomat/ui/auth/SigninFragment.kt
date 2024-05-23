package com.example.stomat.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stomat.Auth
import com.example.stomat.MainActivity
import com.example.stomat.R
import com.google.android.material.textfield.TextInputEditText

class SigninFragment : Fragment(R.layout.fragment_signin) {

    private var authMode = Auth.SIGNIN

    private lateinit var tvSignup: TextView
    private lateinit var tvSignin: TextView
    private lateinit var tvForgot: TextView
    private lateinit var button: Button
    private lateinit var itEmail: TextInputEditText
    private lateinit var itPassword: TextInputEditText

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity?.applicationContext as App).appComponent.inject(this)
        initViews()
        initListeners()
        initObservers()
        updateViews(Auth.SIGNIN)


    }


    private fun initViews() {
        tvSignup = requireView().findViewById(R.id.tvSignup)
        tvSignin = requireView().findViewById(R.id.tvSignin)
        tvForgot = requireView().findViewById(R.id.tvForgot)
        button = requireView().findViewById(R.id.addButton)
        itEmail = requireView().findViewById(R.id.itEmail)
        itPassword = requireView().findViewById(R.id.itPassword)
    }

    private fun updateViews(s: Auth) {
        authMode = s
        itEmail.setText("sergeykozlov.d@ya.ru")
        itPassword.setText("000000")

        var title = ""
        var buttonText = ""
        when (s) {
            Auth.SIGNIN -> {
                title = getString(R.string.sign_in)
                buttonText = getString(R.string.enter)
                // tvForgot.visibility = View.VISIBLE
                tvSignup.visibility = View.VISIBLE
                tvSignin.visibility = View.GONE

            }

            Auth.RECOVERY -> {
                title = getString(R.string.password_recovery)
                buttonText = getString(R.string.recovery)
                //tvForgot.visibility = View.GONE
                tvSignup.visibility = View.VISIBLE
                tvSignin.visibility = View.GONE
            }

            Auth.SIGNUP -> {
                title = getString(R.string.register)
                buttonText = getString(R.string.register)
                //  tvForgot.visibility = View.GONE
                tvSignup.visibility = View.GONE
                tvSignin.visibility = View.VISIBLE
            }
        }

        button.text = buttonText
        (activity as MainActivity).setTitle(title)
    }

    private fun initObservers() {
        viewModel.isDataValid.observe(viewLifecycleOwner) {
            button.isEnabled = it
        }

        viewModel.codeRegisterSent.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.email.value?.let { email ->
                    findNavController().navigateUp()
                    findNavController().navigate(
                        R.id.CodeFragment,
                        CodeFragment.getBundle(email, true)
                    )
                }
            }
        }

        viewModel.codeRecoverySent.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.email.value?.let { email ->
                    findNavController().navigateUp()
                    findNavController().navigate(
                        R.id.CodeFragment,
                        CodeFragment.getBundle(email, false)
                    )
                }
            }
        }

        viewModel.isSignin.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigateUp()
                findNavController().navigate(R.id.ProfileFragment)
            }
        }
    }

    private fun initListeners() {
        tvSignup.setOnClickListener {
            updateViews(Auth.SIGNUP)
        }

        tvSignin.setOnClickListener {
            updateViews(Auth.SIGNIN)
        }

        button.setOnClickListener {
            when (authMode) {
                Auth.SIGNIN -> viewModel.signin()
                Auth.SIGNUP -> viewModel.signup()
                Auth.RECOVERY -> viewModel.recovery()
            }
        }

        itEmail.addTextChangedListener {
            viewModel.email.value = it.toString()
        }

        itPassword.addTextChangedListener {
            viewModel.password.value = it.toString()
        }

        tvForgot.setOnClickListener {
            updateViews(Auth.RECOVERY)
        }
    }
}