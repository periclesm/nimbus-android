package net.cloudfields.nimbus.model

import java.net.URL

class DataAPI {
    companion object {

        val clAltitudeURL: String
            get() {
                return "https://stage.clfd.eu/nimbus/classes/cloudType"
            }

        val clDetailURL: String
            get() {
                return "https://stage.clfd.eu/nimbus/classes/cloudDetail"
            }

        val cloudURL: String
            get() {
                return "https://stage.clfd.eu/nimbus/classes/cloudList"
            }

        val combinedDataURL: String
            get() {
                return "https://stage.clfd.eu/nimbus/classes/cloudList?include=detail&include=type"
            }


        fun getDefaultHeaders(): Map<String, String> {
            val headers = mutableMapOf<String, String>()
            headers["X-Parse-Application-Id"] = "Y6372R67Q2UCqCsWuCnD4YZ3ACEiiFxngF6WNqwE"
            headers["X-Parse-REST-API-Key"] = "LdJJnrhcCEiY8kRA3vAwg4sxT73LhQu84Efmv796"
            return headers
        }

        fun getDefaultBody() {

        }
    }
}