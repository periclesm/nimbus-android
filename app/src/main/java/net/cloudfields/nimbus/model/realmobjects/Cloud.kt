package net.cloudfields.nimbus.model.realmobjects

import io.realm.RealmObject
import io.realm.annotations.*
import org.json.JSONObject

open class Cloud: RealmObject() {

    @PrimaryKey var objectId: String = ""
    @Index var order: Int? = 0
    @Index var initials: String? = ""
    @Index var name: String? = ""
    var excerpt: String? = ""
    var detail: CloudDetail? = null
    var type: CloudAltitude? = null

    companion object {
        fun mapObject(dataObject: JSONObject): Cloud {
            val clObject = Cloud()

            clObject.objectId = dataObject["objectId"] as? String ?: ""
            clObject.order = dataObject["order"] as? Int ?: 0
            clObject.initials = dataObject["initials"] as? String ?: ""
            clObject.name = dataObject["name"] as? String ?: ""
            clObject.excerpt = dataObject["excerpt"] as? String ?: ""

            val detailData = dataObject["detail"] as? JSONObject
            if (detailData != null) {
                val detailObject = CloudDetail.mapObject(detailData)
                //TODO: add to database
                clObject.detail = detailObject
            }

            val typeData = dataObject["type"] as? JSONObject
            if (typeData != null) {
                val typeObject = CloudAltitude.mapObject(typeData)
                //TODO: add to database
                clObject.type = typeObject
            }

            return clObject
        }
    }
}