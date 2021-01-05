package net.cloudfields.nimbus.model.objects

import org.json.JSONObject

class CloudAltitude {

    var objectId: String = ""
    var name: String? = ""
    var detail: String? = ""

    companion object {

        fun mapObject (dataObject: JSONObject): CloudAltitude {
            var clAltitude = CloudAltitude()

            clAltitude.objectId = dataObject["objectId"] as? String ?: ""
            clAltitude.name = dataObject["name"] as? String ?: ""
            clAltitude.detail = dataObject["detail"] as? String ?: ""

            return clAltitude
        }
    }
}