package com.examples.stomat.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.examples.stomat.R
import com.examples.stomat.adapters.BannerAdapter
import com.examples.stomat.adapters.SectionAdapter
import com.examples.stomat.ui.category.SelectCategoryFragment
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var bannerAdapter: BannerAdapter
    lateinit var sectionAdapter: SectionAdapter

    private lateinit var rvBanners: RecyclerView
    private lateinit var rvSections: RecyclerView
//    private lateinit var tvCourses: LinearLayout
//    private lateinit var tvEquipment: LinearLayout
//    private lateinit var tvInstruments: LinearLayout
//    private lateinit var tvLecturers: LinearLayout

    private lateinit var etSearch: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchData()
        initViews()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
//        tvCourses.setOnClickListener {
//            findNavController().navigate(
//                R.id.FragmentLevel2, Level2Fragment.getBundle(
//                    Const.SECTION_COURSES
//                )
//            )
//        }
//
//        tvEquipment.setOnClickListener {
//            findNavController().navigate(
//                R.id.FragmentLevel2, Level2Fragment.getBundle(
//                    Const.SECTION_EQUIPMENT
//                )
//            )
//        }

        etSearch.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                v.clearFocus()
                return@setOnEditorActionListener false
            }

            return@setOnEditorActionListener false
        }
    }

    private fun initViews() {
        bannerAdapter = BannerAdapter()
        sectionAdapter = SectionAdapter{ caategory ->
            findNavController().navigate(
                R.id.FragmentLevel2,
                SelectCategoryFragment.getBundle(
                    caategory
                )
            )
        }

        val view = requireView()

        rvBanners = view.findViewById(R.id.rvBanners)
        rvSections = view.findViewById(R.id.rvSections)
//        tvCourses = view.findViewById(R.id.tvCourses)
//        tvEquipment = view.findViewById(R.id.tvEquipment)
//        tvInstruments = view.findViewById(R.id.tvInstruments)
//        tvLecturers = view.findViewById(R.id.tvLecturers)
        etSearch = view.findViewById(R.id.etSearch)

        rvBanners.adapter = bannerAdapter
        rvSections.adapter = sectionAdapter

    }


    private fun initObservers() {
        viewModel.advertsLiveData.observe(viewLifecycleOwner) {
            bannerAdapter.data(it)
        }

        viewModel.sectionsLiveData.observe(viewLifecycleOwner){
            sectionAdapter.data(it)
        }
    }
}