package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.realmobjects.CloudAltitude

class CloudTypeDAO {
    companion object {
        var typeData: List<CloudAltitude> = listOf()

        fun search(objectId: String): CloudAltitude? {
            val result = typeData.filter { it.objectId == objectId }

            if (!result.isNullOrEmpty()) {
                return result.first() //there can be only one
            }

            return null
        }
    }
}