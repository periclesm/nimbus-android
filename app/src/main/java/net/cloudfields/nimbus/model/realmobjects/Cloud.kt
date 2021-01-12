package net.cloudfields.nimbus.model.realmobjects

import android.os.Handler
import android.os.Looper
import io.realm.RealmObject
import io.realm.annotations.*
import net.cloudfields.nimbus.model.realmmanager.RealmOperation
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
                clObject.detail = detailObject

                Handler(Looper.getMainLooper()).post(Runnable {
                    RealmOperation.add(detailObject)
                })
            }

            val typeData = dataObject["type"] as? JSONObject
            if (typeData != null) {
                val typeObject = CloudAltitude.mapObject(typeData)
                clObject.type = typeObject

                Handler(Looper.getMainLooper()).post(Runnable {
                    RealmOperation.add(typeObject)
                })
            }

            return clObject
        }
    }
}