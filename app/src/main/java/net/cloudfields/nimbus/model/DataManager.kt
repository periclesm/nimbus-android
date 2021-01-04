package net.cloudfields.nimbus.model

import net.cloudfields.nimbus.platform.networker.NetConfig
import net.cloudfields.nimbus.platform.networker.Networker

class DataManager {
    companion object {

        fun prefetchData(callback: (Boolean) -> Unit?) {
            this.getCombinedData { completed ->
                callback(completed)
            }
        }

        fun getCombinedData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
            val config = NetConfig.createWithConfig(requestURL = DataAPI.combinedDataURL, requestHeaders = headers, requestMethod = NetConfig.NetworkerHTTPMethod.GET)

            Networker.getJSONData(config = config, callback = { netResponse ->
                if (netResponse.completed) {
                    val data = netResponse.data as Map<*, *>
                    //map data
                    callback(true)
                }
                else {
                    callback(false)
                }
            })
        }

        fun getAltitudeData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
        }

        fun getDetailData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
        }

        fun getCloudData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
        }
    }
}