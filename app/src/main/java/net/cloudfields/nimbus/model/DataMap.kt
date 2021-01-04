package net.cloudfields.nimbus.model

import com.parse.ParseObject
import net.cloudfields.nimbus.ClassName
import net.cloudfields.nimbus.model.dao.CloudDetailDAO
import net.cloudfields.nimbus.model.dao.CloudTypeDAO
import net.cloudfields.nimbus.model.objects.CloudDetail
import net.cloudfields.nimbus.model.objects.Cloud
import net.cloudfields.nimbus.model.objects.CloudAltitude

class DataMap {
    companion object {

        fun dataMappingForClass(className: ClassName, data: List<ParseObject>): List<Any> {
            val dataMap: MutableList<Any> = mutableListOf()

            when (className) {
                ClassName.cloud -> {
                    for (dataObject: ParseObject in data) {
                        val listObject = Cloud()

                        listObject.objectId = dataObject.objectId
                        listObject.order = dataObject["order"] as? Int
                        listObject.name = dataObject["name"] as? String
                        listObject.initials = dataObject["initials"] as? String
                        listObject.detail = this.objectMapForClass(ClassName.cloudDetail, dataObject["detail"] as? ParseObject) as? CloudDetail
                        listObject.type = this.objectMapForClass(ClassName.cloudAltitude, dataObject["type"] as? ParseObject) as? CloudAltitude

                        dataMap.add(listObject)
                    }
                }

                ClassName.cloudAltitude -> {
                    for (dataObject: ParseObject in data) {
                        val typeObject = CloudAltitude()

                        typeObject.objectId = dataObject.objectId
                        typeObject.detail = dataObject["detail"] as? String
                        typeObject.name = dataObject["name"] as? String

                        dataMap.add(typeObject)
                    }
                }

                ClassName.cloudDetail -> {
                    for (dataObject: ParseObject in data) {
                        val detailObject = CloudDetail()

                        detailObject.objectId = dataObject.objectId
                        detailObject.detail = dataObject["detail"] as? String
                        detailObject.image = dataObject["image"] as? String
                        detailObject.wiki = dataObject["wiki"] as? String

                        dataMap.add(detailObject)
                    }
                }
            }

            return dataMap
        }

        private fun objectMapForClass(className: ClassName, classObject: ParseObject?): Any? {
            if (classObject == null) {
                return null
            }

            when (className) {
                ClassName.cloudAltitude -> {
                    val mappedObject = CloudTypeDAO.search(classObject.objectId)
                    return mappedObject
                }

                ClassName.cloudDetail -> {
                    val mappedObject = CloudDetailDAO.search(classObject.objectId)
                    return mappedObject
                }

                else -> {
                    return null
                }
            }
        }
    }
}