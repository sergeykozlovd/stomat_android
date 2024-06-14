package com.examples.stomat.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.examples.stomat.R
import com.examples.stomat.adapters.CartAdapter
import com.examples.stomat.ui.detail.DetailFragment

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: CartViewModel by viewModels()
    private lateinit var rvItems:RecyclerView
    private lateinit var adapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCartPurchases()
        initViews()
        initAdapter()
        initObservers()

    }

    private fun initAdapter(){
        adapter = CartAdapter{
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