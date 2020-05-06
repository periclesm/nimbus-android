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
                ClassName.List -> {
                    for (dataObject: ParseObject in data) {
                        val listObject = CloudListEntity()

                        listObject.objectId = dataObject.objectId
                        listObject.order = dataObject["order"] as? Int
                        listObject.name = dataObject["name"] as? String
                        listObject.initials = dataObject["initials"] as? String
                        listObject.detail = (dataObject["detail"] as Map<String, String>)["objectId"]
                        listObject.type = (dataObject["type"] as Map<String, String>) ["objectId"]

                        dataMap.add(listObject)
                    }
                }

                ClassName.Type -> {
                    val typeObject = CloudTypeEntity()

                    dataMap.add(typeObject)
                }

                ClassName.Detail -> {
                    val detailObject = CloudDetailEntity()

                    dataMap.add(detailObject)
                }
            }
            return dataMap
        }
    }
}