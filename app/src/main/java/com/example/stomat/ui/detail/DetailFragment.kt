package com.example.stomat.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.stomat.Const
import com.example.stomat.R
import com.example.stomat.model.Advert
import com.google.android.material.button.MaterialButton

class DetailFragment:Fragment(R.layout.fragment_detail) {

    companion object {
        fun getBundle(advert: Advert) = Bundle().apply {
            putSerializable(Const.KEY_ADVERT,advert)
        }
    }
    private val  viewModel: DetailViewModel by viewModels()

    private lateinit var title:TextView
    private lateinit var addButton: MaterialButton
    private lateinit var deleteButton: MaterialButton

    private lateinit var advert: Advert

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParameters()
        initViews()
        initListeners()
    }

    private fun getParameters(){
        arguments?.getSerializable(Const.KEY_ADVERT)?.let {
            advert = it as Advert
        }
    }

    private fun initListeners(){
        addButton.setOnClickListener {
            viewModel.addAdvertToCart(advert)
        }

        deleteButton.setOnClickListener {
            viewModel.deleteFromCart(advert)
        }
    }

    private fun initViews(){
        val view = requireView()
        title = view.findViewById(R.id.title)
        addButton = view.findViewById(R.id.addButton)
        deleteButton = view.findViewById(R.id.deleteButton)

        title.text = advert.title
    }
}