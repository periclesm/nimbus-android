package net.cloudfields.nimbus.model

import android.os.Handler
import android.os.Looper
import io.realm.*
import net.cloudfields.nimbus.model.realmmanager.RealmOperation
import net.cloudfields.nimbus.model.realmobjects.*
import org.json.JSONArray
import org.json.JSONObject


class DataMapHelper {
    companion object {

        fun mapAltitudeData(data: JSONObject) {
            val dataList: RealmList<RealmObject> = RealmList()
            val results = data["results"] as? JSONArray

            if (results != null) {
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    val altitudeObject = CloudAltitude.mapObject(dataObject)
                    dataList.add(altitudeObject)
                }
            }

            if (dataList.isNotEmpty()) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    RealmOperation.add(dataList)
                })
            }
        }

        fun mapDetailData(data: JSONObject) {
            val dataList: RealmList<RealmObject> = RealmList()
            val results = data["results"] as? JSONArray

            if (results != null) {
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    //todo: Commented because will crash. Contains a pointer that I have not yet mapped. in to do list...
                    //val detailObject = CloudDetail.mapObject(dataObject)
                    //dataArray.add(detailObject)
                }

                if (dataList.isNotEmpty()) {
                    Handler(Looper.getMainLooper()).post(Runnable {
                        RealmOperation.add(dataList)
                    })
                }
            }
        }

        fun mapCloudData(data: JSONObject) {
            val dataList: RealmList<RealmObject> = RealmList()
            val results: JSONArray? = data.getJSONArray("results")

            if (results != null)
                for (index in 0 until results.length()) {
                    val dataObject = results.getJSONObject(index)
                    val cloudObject = Cloud.mapObject(dataObject)
                    dataList.add(cloudObject)
                }

            if (dataList.isNotEmpty()) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    RealmOperation.add(dataList)
                })
            }
        }
    }
}