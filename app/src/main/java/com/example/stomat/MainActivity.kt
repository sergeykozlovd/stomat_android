package com.example.stomat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stomat.network.ApiService
import com.example.stomat.network.DataSource
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import org.json.JSONObject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var dataSource: DataSource

    companion object {
        val messageLifeData = MutableLiveData<String>()
    }

    lateinit var navView: BottomNavigationView
    lateinit var navController: NavController
    lateinit var title: TextView
    lateinit var toolbar: Toolbar


    private val authFragments = listOf(
        R.id.ProfileFragment
    )

    private val backFragments = listOf(
        R.id.SigninFragment,
        R.id.SignupFragment,
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
        initViews()
        initNavigation()
        appComponent.inject(this)
        initObservers()
        initListeners()
        //dataSource.register("80diebold08@mail.ru","1234556")
//        AlertUtils.alert(
//            this,
//            message = "okgsldk jgsdlf slkdfjhg lsdkjfhgl skjdfhlgk  jshd flgkdsfg sldk jfghsd lkjf g",
//            callbackOk = {
//
//            },
//            callbackCancel = {
//
//            }
//        )

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
                        dataSource.getUserProfile(){

                        }
                    }

                }

            }

        })

        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }

    private fun initNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.HomeFragment,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.ProfileFragment
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initViews() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView = findViewById( R.id.nav_view)

        title = findViewById(R.id.title)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun initObservers() {
        messageLifeData.observeForever {
            if (it == null) {
                Log.d("qqq", "error network")
            } else {
                //    var boolean: MutableLiveData<ResultBoolean?> = MutableLiveData()
                val obj = JSONObject(it)


                var success = false
                var msg = ""
                try {
                    if (obj.has(Const.SUCCESS)) {
                        success = obj.getBoolean(Const.SUCCESS)
                    }
                    if (obj.has(Const.MESSAGE)) {
                        msg = obj.getString(Const.MESSAGE)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }


        }
    }


}