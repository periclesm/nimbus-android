package net.cloudfields.nimbus.model.entity

import com.parse.ParseObject

class CloudListEntity {
    var objectId: String = ""
    var order: Int? = 9999
    var name: String? = ""
    var initials: String? = ""
    var detail: ParseObject? = null
    var type: ParseObject? = null
}