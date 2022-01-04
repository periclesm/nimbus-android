package net.cloudfields.nimbus.platform.networker

import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetAgent {
    companion object {
        val shared = NetAgent()
    }

    // MARK: - Constructors
    private fun createClient(config: NetConfig): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(config.connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(config.readTimeout, TimeUnit.MILLISECONDS)

        return client.build()
    }

    private fun createRequest(requestURL: String, config: NetConfig): Request {
        val request = Request.Builder()
            .url(config.url)
            .method(config.HTTPMethod.name, null)

        for (header in config.headers) {
            request.addHeader(header.key, header.value)
        }

        return request.build()
    }

    // MARK: - Main Function

    fun getData(config: NetConfig, callback: (NetResponse) -> Unit) {
        val client = this.createClient(config)
        val request = this.createRequest(config.url, config)

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val netResponse = NetResponse.constructResponse(identifier = config.identifier,
                    function = config.function,
                    completed = false,
                    error = e.localizedMessage,
                    data = null)

                callback(netResponse)
            }

            override fun onResponse(call: Call, response: Response) {
                val netResponse = NetResponse.constructResponse(identifier = config.identifier,
                    function = config.function,
                    completed = response.isSuccessful,
                    error = null,
                    data = response.body?.byteStream()
                )

                callback(netResponse)
            }
        })
    }
}