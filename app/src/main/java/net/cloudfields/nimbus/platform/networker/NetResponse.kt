package net.cloudfields.nimbus.platform.networker

import android.annotation.SuppressLint

class NetResponse {

    var identifier: String = ""
    var completed: Boolean = false
    var error: String? = null
    var data: Any? = null

    companion object {

        @SuppressLint("Assert")
        fun constructResponse(identifier: String? = null, completed: Boolean = false, error: String? = null, data: Any? = null): NetResponse {

            val response = NetResponse()

            if (identifier == null) {
                assert(true) { "You must provide an identifier" }
                return NetResponse()
            }

            response.identifier = identifier
            response.completed = completed

            if (error != null) {
                response.error = error
            }

            if (data != null) {
                response.data = data
            }

            return response
        }
    }
}