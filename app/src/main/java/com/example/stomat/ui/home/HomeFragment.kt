package com.example.stomat.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.stomat.R
import com.example.stomat.adapters.AdvertAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var advertAdapter: AdvertAdapter
    private lateinit var rvItems : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAdverts()
        initViews()
        initObservers()

    }

    private fun initViews(){
        advertAdapter = AdvertAdapter()

        rvItems = requireView().findViewById(R.id.rvItems)
        rvItems.adapter  = advertAdapter

    }



    private fun initObservers(){
        viewModel.advertsLiveData.observe(viewLifecycleOwner){
            advertAdapter.data(it)
        }
    }
}