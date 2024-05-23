package com.example.stomat.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.stomat.App
import com.example.stomat.Const
import com.example.stomat.Prefs
import com.example.stomat.R
import com.example.stomat.network.NetUtils
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
//import javax.inject.Inject

class CodeFragment : Fragment(R.layout.fragment_code) {

    companion object{
        fun  getBundle(email:String, isRegister:Boolean) = Bundle().apply {
            putString(Const.KEY_EMAIL,email)
            putBoolean(Const.KEY_IS_REGISTER,isRegister)
        }
    }

//    @Inject
//    private lateinit var apiService: ApiService
    private lateinit var itCode:TextInputEditText 
    private lateinit var button: Button 

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity?.applicationContext as App).appComponent.inject(this)
        initViews()
        initListeners()
    }
    
    private fun initViews(){
        itCode = requireView().findViewById(R.id.itCode)
        button = requireView().findViewById(R.id.addButton)
    }
    
    private var isRegister = true
//    lateinit var binding: FragmentCodeBinding
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        isRegister = arguments?.getBoolean(Const.KEY_IS_REGISTER,true) ?: true
//        (activity?.applicationContext as App).appComponent.inject(this)
//        binding = FragmentCodeinflate(inflater, container, false)
//        initListeners()
//      return root
//    }

    private fun initListeners() {
        button.setOnClickListener {
            val apiService = (activity?.application as App).apiService
            val params = NetUtils.getData(
                Const.KEY_CODE, itCode.text.toString(),
                Const.KEY_EMAIL,arguments?.getString(Const.KEY_EMAIL) ?: ""
            )

            flow {
                try {
                    emit(
                        if (isRegister){
                            apiService.sendRegisterCode(params)
                        } else {
                            apiService.sendRecoveryCode(params)
                        }
                    )
                } catch (e:Exception){
                    e.printStackTrace()
                }

            }.onEach {
                it.token?.let { token ->
                    Prefs.token = token
                    Prefs.savePrefs()
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                }
            }.launchIn(lifecycleScope)

        }


    }
}