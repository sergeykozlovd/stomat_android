package com.examples.stomat.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.examples.stomat.Prefs
import com.examples.stomat.R
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var btnExit: Button
    private lateinit var btnSave: Button
    private lateinit var itEmail: TextInputEditText
    private lateinit var itName: TextInputEditText
    private lateinit var itSurname: TextInputEditText
    private lateinit var itPhone: TextInputEditText
    private lateinit var itCity: TextInputEditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        getUserProfile()
        initObservers()
    }

    private fun initObservers() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            itEmail.setText(it.email)
            itName.setText(it.name)
            itSurname.setText(it.surname)
            itPhone.setText(it.phone)
            itCity.setText(it.city)
        }
    }

    private fun getUserProfile() {
        viewModel.getUserProfile()
    }

    private fun initViews() {
        btnExit = requireView().findViewById(R.id.btnExit)
        btnSave = requireView().findViewById(R.id.btnSave)
        itEmail = requireView().findViewById(R.id.itEmail)
        itName = requireView().findViewById(R.id.itName)
        itSurname = requireView().findViewById(R.id.itSurname)
        itPhone = requireView().findViewById(R.id.itPhone)
        itCity = requireView().findViewById(R.id.itCity)
    }

    private fun initListeners() {
        btnExit.setOnClickListener {
            Prefs.token = null
            Prefs.savePrefs()
            findNavController().popBackStack()
        }

        btnSave.setOnClickListener {
            val  name = itName.text.toString()
            val  surname = itSurname.text
            val  phone = itPhone.text
            val  city = itCity.text
            viewModel.changeUser(
                name,
                surname,
                phone,
                city
            )
        }
    }

}