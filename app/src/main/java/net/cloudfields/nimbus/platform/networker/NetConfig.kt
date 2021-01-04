package net.cloudfields.nimbus.platform.networker

import android.annotation.SuppressLint
import okhttp3.RequestBody

class NetConfig {

    enum class NetworkerFunction {
        json, image, data
    }

    enum class NetworkerHTTPMethod(method: String) {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),         //not yet implemented
        DELETE("DELETE"),   //not yet implemented
        OPTIONS("OPTIONS"), //not yet implemented
    }

    enum class NetworkerCachingMethod {
        Default, IgnoreCached, UseCache, OnlyCache
    }

    var identifier: String = NetUtilities.Identifier()
    var function: NetworkerFunction = NetworkerFunction.json

    var HTTPMethod: NetworkerHTTPMethod = NetworkerHTTPMethod.GET
    var caching: NetworkerCachingMethod = NetworkerCachingMethod.Default
    var connectTimeout: Long = 30000
    var readTimeout: Long = 30000

    var url: String = ""
    var headers: Map<String,String> = mapOf()
    var body: RequestBody? = null
    var sender: Any? = null

    companion object {
        @SuppressLint("Assert")
        fun createWithConfig(requestURL: String = "",
                             requestHeaders: Map<String, String>? = null,
                             requestBody: RequestBody? = null,
                             requestConnectTimeout: Long? = 30000, //timeout should be long
                             requestReadTimeout: Long? = 40000, //timeout should be long
                             requestFunction: NetworkerFunction? = NetworkerFunction.json,
                             requestMethod: NetworkerHTTPMethod? = NetworkerHTTPMethod.GET,
                             requestCaching: NetworkerCachingMethod? = NetworkerCachingMethod.Default,
                             sender: Any? = null): NetConfig {

            val config = NetConfig()

            if (requestURL.isNotBlank()) {
                config.url = requestURL
            }

            if (!requestHeaders.isNullOrEmpty()) {
                config.headers = requestHeaders
            }

            if (requestBody != null) {
                config.body = requestBody
            }

            config.connectTimeout = requestConnectTimeout!!
            config.readTimeout = requestReadTimeout!!
            config.function = requestFunction!!
            config.HTTPMethod = requestMethod!!
            config.caching = requestCaching!!

            config.sender = sender

            return config
        }
    }

}