package net.cloudfields.nimbus.model.realmobjects

import io.realm.RealmObject
import io.realm.annotations.*
import org.json.JSONObject

open class CloudAltitude: RealmObject() {

    @PrimaryKey
    var objectId: String = ""
    @Index var name: String? = ""
    var detail: String? = ""

    companion object {

        fun mapObject (dataObject: JSONObject): CloudAltitude {
            val clAltitude = CloudAltitude()

            clAltitude.objectId = dataObject["objectId"] as? String ?: ""
            clAltitude.name = dataObject["name"] as? String ?: ""
            clAltitude.detail = dataObject["detail"] as? String ?: ""

            return clAltitude
        }
    }
}