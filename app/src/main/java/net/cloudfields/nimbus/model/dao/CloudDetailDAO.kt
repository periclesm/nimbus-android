package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.realmobjects.CloudDetail

class CloudDetailDAO {
    companion object {
        var detailData: List<CloudDetail> = listOf()

        fun search (objectId: String): CloudDetail? {
            val result = detailData.filter { it.objectId == objectId }

            if (!result.isNullOrEmpty()) {
                return result.first() //there can be only one
            }

            return null
        }
    }
}