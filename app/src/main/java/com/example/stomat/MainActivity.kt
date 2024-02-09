package com.example.stomat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stomat.network.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import org.json.JSONObject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var apiService: ApiService

    companion object {
        val messageLifeData = MutableLiveData<String>()
    }

    lateinit var navView: BottomNavigationView
    lateinit var navController: NavController
    lateinit var title: TextView
    lateinit var progress: LinearLayout
    lateinit var toolbar: Toolbar

    private val authFragments = listOf(
        R.id.ProfileFragment
    )

    private val backFragments = listOf(
        R.id.SigninFragment,
        R.id.CodeFragment,
    )

    private val navFragments = listOf(
        R.id.HomeFragment,
        R.id.navigation_dashboard,
        R.id.ProfileFragment,
        R.id.navigation_notifications,
    )

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        viewModel = ViewModelProvider(this,ViewModelFactory(apiService)).get(MainViewModel::class.java)
        initViews()
        initObservers()
        initListeners()
    }

    private fun initListeners(){
        navController.addOnDestinationChangedListener( object: NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.label.isNullOrEmpty()){
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

                if (navFragments.contains(destination.id)){
                    navView.visibility = View.VISIBLE
                } else {
                    navView.visibility = View.GONE
                }

                if (authFragments.contains(destination.id)) {
                    if (Prefs.token.isNullOrEmpty()){
                        //got to signin
                        navController.navigateUp()
                        navController.navigate(R.id.SigninFragment)
                    } else {
                        //get profile
                        viewModel.getUserProfile()
                    }
                }
            }
        })

        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }

    private fun initViews() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView = findViewById( R.id.nav_view)
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
                val obj = JSONObject(it)
                var msg = ""
                try {
                    if (obj.has(Const.MESSAGE)) {
                        msg = obj.getString(Const.MESSAGE)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (msg.isNotEmpty()){
                    AlertUtils.alert(this,msg)
                }
            }
        }
    }

    fun setTitle(text:String){
        title.text = text
    }
}