package net.cloudfields.nimbus.model.realmobjects

import io.realm.RealmObject
import io.realm.annotations.*
import org.json.JSONObject

open class CloudDetail: RealmObject() {

    @PrimaryKey var objectId: String = ""
    var detail: String? = ""
    @Index var image: String? = ""
    @Index var wiki: String? = ""

    companion object {

        fun mapObject (dataObject: JSONObject): CloudDetail {
            val clDetail = CloudDetail()

            clDetail.objectId = dataObject["objectId"] as? String ?: ""
            clDetail.detail = dataObject["detail"] as? String ?: ""
            clDetail.image = dataObject["image"] as? String ?: ""
            clDetail.wiki = dataObject["wiki"] as? String ?: ""

            return clDetail
        }
    }
}