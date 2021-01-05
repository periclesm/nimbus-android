package net.cloudfields.nimbus.platform.networker

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class NetResponse {

    var identifier: String = ""

    var function: NetConfig.NetworkerFunction = NetConfig.NetworkerFunction.json
    var completed: Boolean = false
    var error: String? = null
    var data: Any? = null

    companion object {

        @SuppressLint("Assert")
        fun constructResponse(
            identifier: String? = null,
            function: NetConfig.NetworkerFunction,
            completed: Boolean = false,
            error: String? = null,
            data: Any? = null
        ): NetResponse {

            val response = NetResponse()

            if (identifier == null) {
                assert(true) { "You must provide an identifier" }
                return NetResponse()
            }

            response.identifier = identifier
            response.function = function
            response.completed = completed

            if (error != null) {
                response.error = error
            }

            if (data != null) {
                when (function) {
                    NetConfig.NetworkerFunction.json -> {
                        response.data = this.dataToJSON(data as InputStream)
                    }

                    NetConfig.NetworkerFunction.image -> {
                        response.data = this.dataToBitmap(data as InputStream)
                    }

                    NetConfig.NetworkerFunction.data -> {
                        response.data = data as InputStream
                    }
                }
            }

            return response
        }

        private fun dataToJSON(data: InputStream): JSONObject? {
            try {
                val streamReader = BufferedReader(InputStreamReader(data, "UTF-8"))
                val responseStrBuilder = StringBuilder()

                var inputStr: String?
                while (streamReader.readLine().also { inputStr = it } != null)
                    responseStrBuilder.append(inputStr)

                val json = JSONObject(responseStrBuilder.toString())
                return json

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return null
        }

        private fun dataToBitmap(data: InputStream): Bitmap {
            val bitmap = BitmapFactory.decodeStream(data)
            return bitmap
        }
    }
}