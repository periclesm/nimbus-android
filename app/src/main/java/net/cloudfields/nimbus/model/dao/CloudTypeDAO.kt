package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.entity.CloudTypeEntity

class CloudTypeDAO {
    companion object {
        var typeData: List<CloudTypeEntity> = listOf()

        fun search(objectId: String): CloudTypeEntity? {
            val result = typeData.filter { it.objectId == objectId }

            if (!result.isNullOrEmpty()) {
                return result.first() //there can be only one
            }

            return null
        }
    }
}