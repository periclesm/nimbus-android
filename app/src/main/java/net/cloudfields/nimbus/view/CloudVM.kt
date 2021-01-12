package net.cloudfields.nimbus.view

import io.realm.RealmList
import net.cloudfields.nimbus.controller.CloudController
import net.cloudfields.nimbus.model.realmobjects.Cloud

class CloudVM {
    companion object {
        var shared = CloudVM()
    }

    var cloudData: List<Cloud> = listOf()
    var selectedCloud: Cloud? = Cloud()

    fun getData() {
        cloudData = CloudController.getCloudData("name")
    }

    fun refreshData() {
        // not yet implemented
    }
}