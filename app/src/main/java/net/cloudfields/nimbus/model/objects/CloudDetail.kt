package net.cloudfields.nimbus.model.objects

class CloudDetail {

    var objectId: String = ""
    var detail: String? = ""
    var image: String? = ""
    var wiki: String? = ""

    companion object {

        fun mapObject (dataObject: Map<*, *>): CloudDetail {
            val clDetail = CloudDetail()

            clDetail.objectId = dataObject["objectId"] as? String ?: ""
            clDetail.detail = dataObject["detail"] as? String ?: ""
            clDetail.image = dataObject["image"] as? String ?: ""
            clDetail.wiki = dataObject["wiki"] as? String ?: ""

            return clDetail
        }
    }
}