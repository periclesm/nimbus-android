package net.cloudfields.nimbus.model.dao

import net.cloudfields.nimbus.model.entity.CloudListEntity

class CloudListDAO {
    companion object {
        var listData: List<CloudListEntity> = listOf()
        var selectedObject: CloudListEntity? = null
    }
}