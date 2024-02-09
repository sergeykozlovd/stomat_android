package com.example.stomat

object Prefs {
    var pinCode = Const.PINCODE_VALUE
    var userId: Int? = null
    var token: String? = null

    fun savePrefs(){
        with(App.prefs.edit()) {
            putString(Const.KEY_PINCODE, pinCode)
            apply()
        }
    }

    fun loadPrefs(){
        App.prefs.getString(Const.KEY_PINCODE,Const.PINCODE_VALUE)?.let {
            pinCode = it
        }
    }
}