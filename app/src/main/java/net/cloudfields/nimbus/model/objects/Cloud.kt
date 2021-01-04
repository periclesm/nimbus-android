package net.cloudfields.nimbus.model.objects

class Cloud {

    var objectId: String = ""
    var order: Int? = 0
    var initials: String? = ""
    var name: String? = ""
    var excerpt: String? = ""
    var detail: CloudDetail? = CloudDetail()
    var type: CloudAltitude? = CloudAltitude()

    companion object {

        fun mapObject(dataObject: Map<String, Any>): Cloud {
            val clObject = Cloud()

            clObject.objectId = dataObject["objectId"] as? String ?: ""
            clObject.order = dataObject["order"] as? Int ?: 0
            clObject.initials = dataObject["initials"] as? String ?: ""
            clObject.name = dataObject["name"] as? String ?: ""
            clObject.excerpt = dataObject["excerpt"] as? String ?: ""

            val detailData = dataObject["detail"] as? Map<*, *>
            if (detailData != null) {
                val detailObject = CloudDetail.mapObject(detailData)
                //add to database
                clObject.detail = detailObject
            }

            val typeData = dataObject["type"] as? Map<*, *>
            if (typeData != null) {
                val typeObject = CloudAltitude.mapObject(typeData)
                //add to database
                clObject.type = typeObject
            }

            return clObject
        }
    }
}