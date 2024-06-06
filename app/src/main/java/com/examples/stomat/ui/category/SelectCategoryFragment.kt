package com.examples.stomat.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.examples.stomat.Const
import com.examples.stomat.MainActivity
import com.examples.stomat.R
import com.examples.stomat.adapters.CategoryAdapter
import com.examples.stomat.model.Category

//TODO rename to SelectCategory
class SelectCategoryFragment : Fragment(R.layout.fragment_select_category) {
    companion object {
        fun getBundle(category: Category) = Bundle().apply {
            putSerializable(Const.KEY_CATEGORY, category)
        }
    }

    private var category: Category? = null
    private val viewModel: SelectCategoryViewModel by viewModels()
    private lateinit var rvCategories: RecyclerView
    private var adapter: CategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        getData()
        initViews()
        initAdapter()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.categoriesLifeData.observe(viewLifecycleOwner) {
            adapter?.fillData(it)
        }
    }

    private fun getData() {
        category?.id?.let { viewModel.getCategories(it) }
    }

    private fun initListeners() {

    }

    private fun initArguments() {
        category = arguments?.getSerializable(Const.KEY_CATEGORY) as Category
    }

    private fun initAdapter() {
        adapter = CategoryAdapter {
            (requireActivity() as MainActivity).navToCatalog(it)
        }
        rvCategories.adapter = adapter
    }

    private fun initViews() {
        category?.name?.let { (activity as MainActivity).setTitle(it) }
        rvCategories = requireView().findViewById(R.id.rvCategories)
    }
}


