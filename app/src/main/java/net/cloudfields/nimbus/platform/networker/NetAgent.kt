package net.cloudfields.nimbus.platform.networker

import okhttp3.*
import java.io.IOException

class NetAgent {
    companion object {
        val shared = NetAgent()
    }

    // MARK: - Synthesis
    private fun createRequest(requestURL: String, config: NetConfig): Request {
        val request = Request.Builder()
            .url(config.url)
            .addHeader("X-Parse-Application-Id", "Y6372R67Q2UCqCsWuCnD4YZ3ACEiiFxngF6WNqwE")
            .addHeader("X-Parse-REST-API-Key", "LdJJnrhcCEiY8kRA3vAwg4sxT73LhQu84Efmv796")
            .build()

        return request
    }

    // MARK: - Main Methods

    fun getData(config: NetConfig, function: NetConfig.NetworkerFunction, callback: (NetResponse) -> Unit) {
        val client = OkHttpClient()
        val request = createRequest(config.url, config)

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val netResponse = NetResponse.constructResponse(identifier = config.identifier,
                    completed = false,
                    error = e.localizedMessage,
                    data = null)

                callback(netResponse)
            }

            override fun onResponse(call: Call, response: Response) {
                //TODO("Not yet implemented")
                val result = NetResponse.constructResponse(identifier = config.identifier,
                    completed = response.isSuccessful,
                    error = null,
                    data = response.body())

                callback(result)
            }

        })
    }
}