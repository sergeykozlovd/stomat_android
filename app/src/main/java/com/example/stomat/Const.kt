package com.example.stomat

object Const {
    val base = "http://192.168.43.250:8000" // note 8
//    val base = "http://169.254.83.53:8000" // no net
    val baseUrl = "$base/api/"
    val baseImageUrl = "$base/storage/"
    val KEY_EMAIL = "email"
    val KEY_IS_REGISTER = "KEY_IS_REGISTER"
    val KEY_CATEGORY = "KEY_CATEGORY"
    val KEY_ADVERT = "KEY_ADVERT"
    val KEY_TITLE = "title"
    val MESSAGE = "message"
    val KEY_PINCODE = "pincode"
    val KEY_TOKEN = "token"
    val KEY_CODE = "code"
    val PINCODE_VALUE = "000000"

    val SECTION_COURSES = 0
    val SECTION_EQUIPMENT = 1
    val SECTION_INSTRUMENTS = 2
    val SECTION_LECTORS = 3
}

enum  class Auth {
    SIGNIN,
    RECOVERY,
    SIGNUP
}

val authFragments = listOf(
    R.id.ProfileFragment
)

val backFragments = listOf(
    R.id.SigninFragment,
    R.id.CodeFragment,
    R.id.FragmentLevel2,
    R.id.DetailFragment,
)

val navFragments = listOf(
    R.id.HomeFragment,
    R.id.CatalogFragment,
    R.id.CartFragment,
    R.id.ProfileFragment,
    R.id.navigation_notifications,
)

val fragmentsWithSoftResize = listOf(
    R.id.SigninFragment,
)