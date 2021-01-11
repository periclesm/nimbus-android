package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.realmobjects.Cloud

class CloudListDAO {
    companion object {
        var listData: List<Cloud> = listOf()
        var selectedObject: Cloud? = null
    }
}