package net.cloudfields.nimbus.model

import net.cloudfields.nimbus.model.objects.*

class DataMapper {
    companion object {

        fun mapAltitudeData(data: Map<*, *>) {
            val dataArray: Array<CloudAltitude> = arrayOf()
            val results = data["results"] as? Array<Map<String, Any>>

            if (results != null) {
                for (dataObject in results) {
                    val altitudeObject = CloudAltitude.mapObject(dataObject)
                    dataArray.plus(altitudeObject)
                }
            }

            if (dataArray.isNotEmpty()) {
                //RealmOperation.add(dataArray: dataArray, updatePolicy: .modified)
            }
        }

        fun mapDetailData(data: Map<*, *>) {
            val dataArray: Array<CloudDetail> = arrayOf()
            val results = data["results"] as? Array<Map<String, Any>>

            if (results != null) {
                for (dataObject in results) {
                    val detailObject = CloudDetail.mapObject(dataObject)
                    dataArray.plus(detailObject)
                }

                if (dataArray.isNotEmpty()) {
                    //RealmOperation.add(dataArray: dataArray, updatePolicy: .modified)
                }
            }
        }

        fun mapCloudData(data: Map<*, *>) {
            val dataArray: Array<Cloud> = arrayOf()
            val results = data["results"] as? Array<Map<String, Any>>

            if (results != null)
                for (dataObject in results) {
                    val cloudObject = Cloud.mapObject(dataObject)
                    dataArray.plus(cloudObject)
                }

            if (dataArray.isNotEmpty()) {
                //RealmOperation.add(dataArray: dataArray, updatePolicy:.modified)
            }
        }
    }
}