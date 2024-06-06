package com.examples.stomat.network

object NetUtils {

    /**
     * "param", value,
     */
    fun getData(vararg param: String)  :HashMap<String,String>{

        val hashMap  = hashMapOf<String,String>()

        if (param.size % 2 != 1 ) {
            var key = ""
            var value:String

            param.forEachIndexed { index, s ->
                if (index % 2 == 0){
                    key = s
                } else {
                    value = s
                    hashMap[key] = value
                }
            }
        }

        return hashMap
    }
}