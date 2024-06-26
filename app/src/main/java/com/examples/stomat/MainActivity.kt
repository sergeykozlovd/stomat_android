package com.examples.stomat

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        val messageLifeData = MutableLiveData<Response?>()
    }

    var categoryId: Int? = null
    lateinit var navView: BottomNavigationView
    lateinit var navController: NavController
    lateinit var title: TextView
    lateinit var progress: LinearLayout
    lateinit var toolbar: Toolbar


    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.label.isNullOrEmpty()) {
                    title.text = ""
                    toolbar.visibility = View.GONE
                } else {
                    title.text = destination.label
                    toolbar.visibility = View.VISIBLE
                }

                if (backFragments.contains(destination.id)) {
                    toolbar.navigationIcon = getDrawable(R.drawable.ic_back)
                } else {
                    toolbar.navigationIcon = null
                }

                if (navFragments.contains(destination.id)) {
                    navView.visibility = View.VISIBLE
                } else {
                    navView.visibility = View.GONE
                }

                if (authFragments.contains(destination.id)) {
                    if (Prefs.token.isNullOrEmpty()) {
                        //got to signin
                        navController.navigateUp()
                        navController.navigate(R.id.SigninFragment)
                    } else {
                        //get profile
//                        viewModel.getUserProfile()
                    }
                }

                if (fragmentsWithSoftResize.contains(destination.id)) {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                } else {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
                }


            }
        })

        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }

    private fun initViews() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView = findViewById(R.id.nav_view)
        title = findViewById(R.id.title)
        toolbar = findViewById(R.id.toolbar)
        progress = findViewById(R.id.progress)
        navView.setupWithNavController(navController)
    }

    private fun initObservers() {
        messageLifeData.observeForever {
            if (it == null) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
                val code = it.code
                val message = it.message

                if (code != 500 ){
                    try {
                        val str = it.body?.string()
                        val obj = JSONObject(str)
                        var msg = ""

                        if (obj.has(Const.MESSAGE)) {
                            msg = obj.getString(Const.MESSAGE)
                        }

                        if (msg.isNotEmpty()) {
                            AlertUtils.alert(this, msg)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    AlertUtils.alert(this, "CODE:n$code  $message ${it.request.url}")
                }
            }
        }
    }

    fun setTitle(text: String) {
        if (text.isEmpty()) {
            toolbar.visibility = View.GONE
        } else {
            title.text = text
            toolbar.visibility = View.VISIBLE
        }
    }

    fun navToCatalog(categoryId: Int) {
        this.categoryId = categoryId
        navController.navigateUp()
        navView.selectedItemId = R.id.CatalogFragment
    }
}