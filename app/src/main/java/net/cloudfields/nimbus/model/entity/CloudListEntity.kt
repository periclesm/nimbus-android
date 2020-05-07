package net.cloudfields.nimbus.model.entity

import com.parse.ParseObject

class CloudListEntity {
    var objectId: String = ""
    var order: Int? = 9999
    var name: String? = ""
    var initials: String? = ""
    var detail: CloudDetailEntity? = CloudDetailEntity()
    var type: CloudTypeEntity? = CloudTypeEntity()
}