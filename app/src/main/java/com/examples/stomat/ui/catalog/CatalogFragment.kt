package com.examples.stomat.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.examples.stomat.MainActivity
import com.examples.stomat.R
import com.examples.stomat.adapters.AdvertAdapter
import com.examples.stomat.ui.detail.DetailFragment

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var rvItems:RecyclerView
    private lateinit var adapter:AdvertAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAdverts(getCategoryId())
        initViews()
        initAdapter()
        initObservers()
    }

    private fun initObservers(){
        viewModel.advertsLiveData.observe(viewLifecycleOwner){
            adapter.fillData(it)
        }
    }

    private fun initAdapter(){
        adapter = AdvertAdapter{
            findNavController().navigate(R.id.DetailFragment,DetailFragment.getBundle(it))
        }

        rvItems.adapter = adapter
    }

    private fun initViews(){
        rvItems = requireView().findViewById(R.id.rvBanners)
    }

    private fun getCategoryId() : Int? {
        val categoryId = (activity as MainActivity).categoryId
        (activity as MainActivity).categoryId = null
        return  categoryId
    }
}