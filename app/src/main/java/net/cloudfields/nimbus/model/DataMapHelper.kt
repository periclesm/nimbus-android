package net.cloudfields.nimbus.model

import net.cloudfields.nimbus.model.realmobjects.*
import org.json.JSONArray
import org.json.JSONObject


class DataMapHelper {
    companion object {

        fun mapAltitudeData(data: JSONObject) {
            val dataArray: Array<CloudAltitude> = arrayOf()
            val results = data["results"] as? JSONArray

            if (results != null) {
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    val altitudeObject = CloudAltitude.mapObject(dataObject)
                    dataArray.plus(altitudeObject)
                }
            }

            if (dataArray.isNotEmpty()) {
                //TODO: RealmOperation.add(dataArray: dataArray, updatePolicy: .modified)
            }
        }

        fun mapDetailData(data: JSONObject) {
            val dataArray: Array<CloudDetail> = arrayOf()
            val results = data["results"] as? JSONArray

            if (results != null) {
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    //note: this wil lcrash because it contains a pointer that I have not yet mapped. in to do list...
                    //val detailObject = CloudDetail.mapObject(dataObject)
                    //dataArray.plus(detailObject)
                }

                if (dataArray.isNotEmpty()) {
                    //TODO: RealmOperation.add(dataArray: dataArray, updatePolicy: .modified)
                }
            }
        }

        fun mapCloudData(data: JSONObject) {
            val dataArray: List<Cloud> = mutableListOf()
            val results: JSONArray? = data.getJSONArray("results")

            if (results != null)
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    val cloudObject = Cloud.mapObject(dataObject)
                    dataArray.plus(cloudObject)
                }

            if (dataArray.isNotEmpty()) {
                //TODO: RealmOperation.add(dataArray: dataArray, updatePolicy:.modified)
            }
        }
    }
}