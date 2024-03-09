package com.example.stomat.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.stomat.Prefs
import com.example.stomat.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var btnExit: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
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