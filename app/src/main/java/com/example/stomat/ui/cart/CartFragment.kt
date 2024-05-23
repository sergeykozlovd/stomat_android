package com.example.stomat.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stomat.R
import com.example.stomat.adapters.AdvertAdapter
import com.example.stomat.ui.detail.DetailFragment

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: CartViewModel by viewModels()
    private lateinit var rvItems:RecyclerView
    private lateinit var adapter:AdvertAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCartPurchases()
        initViews()
        initAdapter()
        initObservers()

    }

    private fun initAdapter(){
        adapter = AdvertAdapter{
            findNavController().navigate(R.id.DetailFragment, DetailFragment.getBundle(it))
        }

        rvItems.adapter = adapter
    }
    private fun initViews(){
        val view = requireView()
        rvItems = view.findViewById(R.id.rvBanners)
    }

    private fun initObservers() {
        viewModel.cartPurchases.observe(viewLifecycleOwner) {
            adapter.fillData(it)
        }
    }
}