package com.examples.stomat.network

import com.examples.stomat.MainActivity
import com.examples.stomat.Prefs
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http.RealResponseBody
import java.lang.Exception

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var response: Response? = null
        var bodyString: String? = null
        var response2: Response? = null

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
           // bodyString = "{\"message\":\"NETWORK ERROR\",\"success\":false}"
        }

        response = Response.Builder().apply {
            code(response?.code ?: 500)
            message(response?.message ?: "Server Offline")
//                body(response.body)
            body((bodyString ?: "").toResponseBody("text/html".toMediaType()))
            request(response?.request ?: chain.request())
            protocol(Protocol.HTTP_1_0)
        }.build()
//        if (response?.code != 200) {
        MainActivity.messageLifeData.postValue(response)
//
//        }


        return Response.Builder()
            .apply {
                code(response.code )
                message(response.message)
                body((bodyString ?: "").toResponseBody("text/html".toMediaType()))
                request(response.request )
                protocol(Protocol.HTTP_1_0)
            }.build()
    }
}