package com.example.stomat.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.stomat.R

class ProfileFragment:Fragment(R.layout.fragment_profile) {
    private val TAG = "ProfileFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
    }
}