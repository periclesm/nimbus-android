package net.cloudfields.nimbus.controller

import io.realm.RealmList
import io.realm.Sort
import io.realm.kotlin.where
import net.cloudfields.nimbus.model.realmmanager.*
import net.cloudfields.nimbus.model.realmobjects.Cloud
import java.net.URL

class CloudController {
    companion object {

        fun getCloudCount(): Long {
            val count = RealmDatabase.shared.db.where(Cloud::class.java)?.count()
            if (count != null) {
                return count
            }

            return 0
        }

        fun getCloud(objectId: String): Cloud? {
            val cloud = RealmDatabase.shared.db.where(Cloud::class.java).equalTo("objectId", objectId).findFirst()
            return cloud
        }

        fun getCloudData(sortBy: String, ascending: Sort = Sort.ASCENDING): RealmList<Cloud> {
            //val results = RealmQuery.sort(rObject = Cloud, sortAttribute = sortBy, ascending = ascending)
            val results = RealmDatabase.shared.db.where(Cloud::class.java).sort(sortBy, ascending)
            return RealmQuery.resultsToListConversion(results)
        }

        fun getImageURL(objectId: String): URL? {
            val cloud = this.getCloud(objectId)
            if (cloud != null) {
                return URL(cloud.detail?.image)
            }

            return null
        }

        fun getWikiURL(objectId: String): URL? {
            val cloud = this.getCloud(objectId)
            if (cloud != null) {
                return URL(cloud.detail?.wiki)
            }

            return null
        }
    }
}