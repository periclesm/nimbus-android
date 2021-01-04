package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.objects.Cloud

class CloudListDAO {
    companion object {
        var listData: List<Cloud> = listOf()
        var selectedObject: Cloud? = null
    }
}