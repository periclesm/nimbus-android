package net.cloudfields.nimbus.model

import net.cloudfields.nimbus.platform.networker.*
import org.json.JSONObject

class DataManager {
    companion object {

        fun prefetchData(callback: (Boolean) -> Unit?) {
            this.getCombinedData { completed ->
                callback(completed)
            }

            //just testing, don't bother...
//            this.getAltitudeData {
//                this.getDetailData {
//                    //this.getCloudData { }
//                }
//            }
        }

        fun getCombinedData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
            val config = NetConfig.createWithConfig(requestURL = DataAPI.combinedDataURL, requestHeaders = headers, requestMethod = NetConfig.NetworkerHTTPMethod.GET)

            Networker.getJSONData(config = config, callback = { netResponse ->
                if (netResponse.completed) {
                    val data = netResponse.data as JSONObject
                    DataMapHelper.mapCloudData(data)
                    callback(true)
                }
                else {
                    callback(false)
                }
            })
        }

        fun getAltitudeData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
            val config = NetConfig.createWithConfig(requestURL = DataAPI.clAltitudeURL, requestHeaders = headers, requestMethod = NetConfig.NetworkerHTTPMethod.GET)

            Networker.getJSONData(config, callback = { netResponse ->
                if (netResponse.completed) {
                    val data = netResponse.data as JSONObject
                    DataMapHelper.mapAltitudeData(data)
                    callback(true)
                }
                else {
                    callback(false)
                }
            })
        }

        fun getDetailData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
            val config = NetConfig.createWithConfig(requestURL = DataAPI.clDetailURL, requestHeaders = headers, requestMethod = NetConfig.NetworkerHTTPMethod.GET)

            Networker.getJSONData(config, callback = { netResponse ->
                if (netResponse.completed) {
                    val data = netResponse.data as JSONObject
                    DataMapHelper.mapDetailData(data)
                    callback(true)
                }
                else {
                    callback(false)
                }
            })
        }

        fun getCloudData(callback: (Boolean) -> Unit) {
            val headers = DataAPI.getDefaultHeaders()
            val config = NetConfig.createWithConfig(requestURL = DataAPI.cloudURL, requestHeaders = headers, requestMethod = NetConfig.NetworkerHTTPMethod.GET)

            Networker.getJSONData(config, callback = { netResponse ->
                if (netResponse.completed) {
                    val data = netResponse.data as JSONObject
                    DataMapHelper.mapCloudData(data)
                    callback(true)
                }
                else {
                    callback(false)
                }
            })
        }
    }
}