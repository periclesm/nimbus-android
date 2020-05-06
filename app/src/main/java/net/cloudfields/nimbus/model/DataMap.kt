package net.cloudfields.nimbus.model

import com.parse.ParseObject
import net.cloudfields.nimbus.ClassName
import net.cloudfields.nimbus.model.entity.CloudDetailEntity
import net.cloudfields.nimbus.model.entity.CloudListEntity
import net.cloudfields.nimbus.model.entity.CloudTypeEntity

class DataMap {
    companion object {

        fun dataMappingForClass(className: ClassName, data: List<ParseObject>): List<Any> {
            val dataMap: ArrayList<Any> = arrayListOf()

            when (className) {
                ClassName.cloudList -> {
                    for (dataObject: ParseObject in data) {
                        val listObject = CloudListEntity()

                        listObject.objectId = dataObject.objectId
                        listObject.order = dataObject["order"] as? Int
                        listObject.name = dataObject["name"] as? String
                        listObject.initials = dataObject["initials"] as? String
                        listObject.detail = dataObject["detail"] as? ParseObject
                        listObject.type = dataObject["type"]  as? ParseObject

                        dataMap.add(listObject)
                    }
                }

                ClassName.cloudType -> {
                    val typeObject = CloudTypeEntity()

                    dataMap.add(typeObject)
                }

                ClassName.cloudDetail -> {
                    val detailObject = CloudDetailEntity()

                    dataMap.add(detailObject)
                }
            }
            return dataMap
        }
    }
}