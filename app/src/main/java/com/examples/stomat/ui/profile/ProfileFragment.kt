package com.examples.stomat.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.examples.stomat.Prefs
import com.examples.stomat.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var btnExit: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        getUserProfile()
    }

    private fun getUserProfile(){
        viewModel.getUserProfile()
    }

    private fun initViews() {
        btnExit = requireView().findViewById(R.id.btnExit)
    }

    private fun initListeners() {
        btnExit.setOnClickListener {
            Prefs.token = null
            Prefs.savePrefs()
            findNavController().popBackStack()
        }
    }

}