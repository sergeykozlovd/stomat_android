package com.examples.stomat

object Prefs {
    var pinCode = Const.PINCODE_VALUE
    var userId: Int? = null
    var token: String? = null

    fun savePrefs(){
        with(App.prefs.edit()) {
            putString(Const.KEY_PINCODE, pinCode)
            putString(Const.KEY_TOKEN, token)
            apply()
        }
    }

    fun loadPrefs(){
        App.prefs.getString(Const.KEY_PINCODE,Const.PINCODE_VALUE)?.let {
            pinCode = it
        }

        token = App.prefs.getString(Const.KEY_TOKEN,null)
    }
}