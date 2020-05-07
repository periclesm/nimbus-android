package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.entity.CloudDetailEntity

class CloudDetailDAO {
    companion object {
        var detailData: List<CloudDetailEntity> = listOf()

        fun search (objectId: String): CloudDetailEntity? {
            val result = detailData.filter { it.objectId == objectId }

            if (!result.isNullOrEmpty()) {
                return result.first() //there can be only one
            }

            return null
        }
    }
}