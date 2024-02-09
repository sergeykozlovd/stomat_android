package com.example.stomat.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.stomat.App
import com.example.stomat.Const
import com.example.stomat.R
import com.example.stomat.databinding.FragmentCodeBinding
import com.example.stomat.network.ApiService
import com.example.stomat.network.NetUtils
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CodeFragment : Fragment(R.layout.fragment_code) {

    companion object{
        fun  getBundle(email:String) = Bundle().apply {
            putString(Const.EMAIL,email)
        }
    }

    @Inject
    lateinit var apiService: ApiService

    lateinit var binding: FragmentCodeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        initListeners()

//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    private fun initListeners() {
        binding.button.setOnClickListener {
            val params = NetUtils.getData(
                Const.KEY_CODE, binding.itCode.text.toString(),
                Const.EMAIL,arguments?.getString(Const.EMAIL) ?: ""
            )

            flow {
                try {
                    emit(apiService.sendRecoveryCode(params))
                } catch (e:Exception){
                    e.printStackTrace()
                }

            }.onEach {
it

            }.launchIn(lifecycleScope)

        }


    }
}