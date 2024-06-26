package com.examples.stomat.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.examples.stomat.Auth
import com.examples.stomat.MainActivity
import com.examples.stomat.R
import com.google.android.material.textfield.TextInputEditText

class SigninFragment : Fragment(R.layout.fragment_signin) {

    private var authMode = Auth.SIGNIN

    private lateinit var tvSignup: TextView
    private lateinit var tvSignin: TextView
    private lateinit var tvForgot: TextView
    private lateinit var button: Button
    private lateinit var itEmail: TextInputEditText
    private lateinit var itPassword: TextInputEditText
    private lateinit var itName: TextInputEditText
    private lateinit var itSurname: TextInputEditText
    private lateinit var itPhone: TextInputEditText
    private lateinit var itCity: TextInputEditText
    private lateinit var registerBlock: LinearLayout

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
        registerBlock = requireView().findViewById(R.id.registerBlock)
        itName = requireView().findViewById(R.id.itName)
        itSurname = requireView().findViewById(R.id.itSurname)
        itPhone = requireView().findViewById(R.id.itPhone)
        itCity = requireView().findViewById(R.id.itCity)

    }

    private fun updateViews(authMode_: Auth) {
        authMode = authMode_
        viewModel.isSignin.value = authMode_ == Auth.SIGNIN
        itEmail.setText("sergeykozlov.d@ya.ru")
        itPassword.setText("000000")
        itName.setText("Sergey")
        itSurname.setText("Kozlov")
        itPhone.setText("77472496278")
        itCity.setText("Schuchinsk")

        var title = ""
        var buttonText = ""
        when (authMode_) {
            Auth.SIGNIN -> {
                title = getString(R.string.sign_in)
                buttonText = getString(R.string.enter)
                tvSignup.visibility = View.VISIBLE
                tvSignin.visibility = View.GONE
                registerBlock.visibility = View.GONE

            }

            Auth.RECOVERY -> {
                title = getString(R.string.password_recovery)
                buttonText = getString(R.string.recovery)
                tvSignup.visibility = View.VISIBLE
                tvSignin.visibility = View.GONE
                registerBlock.visibility = View.GONE
            }

            Auth.SIGNUP -> {
                title = getString(R.string.register)
                buttonText = getString(R.string.register)
                tvSignup.visibility = View.GONE
                tvSignin.visibility = View.VISIBLE
                registerBlock.visibility = View.VISIBLE
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

//        viewModel.isSignin.observe(viewLifecycleOwner) {
//            if (it) {
//                findNavController().navigateUp()
//                findNavController().navigate(R.id.ProfileFragment)
//            }
//        }
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
        itName.addTextChangedListener {
            viewModel.name.value = it.toString()
        }
        itSurname.addTextChangedListener {
            viewModel.surname.value = it.toString()
        }
        itPhone.addTextChangedListener {
            viewModel.phone.value = it.toString()
        }
        itCity.addTextChangedListener {
            viewModel.city.value = it.toString()
        }

        tvForgot.setOnClickListener {
            updateViews(Auth.RECOVERY)
        }
    }
}