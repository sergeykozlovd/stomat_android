package com.example.stomat

object Const {
//    val baseUrl = "http://192.168.43.250:8000/api/"
    val baseUrl = "http://192.168.60.250:8000/api/"
//    val baseUrl = "http://192.168.1.108:8080/api/"
    val KEY_EMAIL = "email"
    val KEY_IS_REGISTER = "KEY_IS_REGISTER"
    val PASSWORD = "password"
    val SUCCESS = "success"
    val MESSAGE = "message"
    val KEY_PINCODE = "pincode"
    val KEY_CODE = "code"
    val PINCODE_VALUE = "000000"
}

enum  class Auth {
    SIGNIN ,
    RECOVERY,
    SIGNUP
}