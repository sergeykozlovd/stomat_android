package com.example.stomat.network

import com.example.stomat.MainActivity
import com.example.stomat.Prefs
import com.example.stomat.model.ResponseData
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.lang.Exception

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var response: Response? = null
        var bodyString: String? = null

        MainActivity.messageLifeData.postValue(null)
        val requestBuilder = chain.request().newBuilder()

        Prefs.token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        try {
            response = chain.proceed(
                requestBuilder.build().newBuilder().build()
            )

            bodyString = response.body?.string()

        } catch (e: Exception) {
            e.printStackTrace()
            bodyString = "{\"message\":\"NETWORK ERROR\",\"success\":false}"
        }

//        if (response?.code != 200) {
        MainActivity.messageLifeData.postValue(bodyString)
//
//        }


        return Response.Builder()
            .apply {
                code(response?.code ?: 444)
                message(response?.message ?: "error")
                body((bodyString ?: "").toResponseBody("text/html".toMediaType()))
                request(response?.request ?: chain.request())
                protocol(Protocol.HTTP_1_0)
            }.build()
    }
}