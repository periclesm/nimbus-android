package net.cloudfields.nimbus.controller

import io.realm.*
import net.cloudfields.nimbus.model.realmmanager.*
import net.cloudfields.nimbus.model.realmobjects.Cloud
import java.net.URL


class CloudController {
    companion object {

        fun getCloudCount(): Long {
            val count = RealmDatabase.shared.db.where(Cloud::class.java).count()
            if (count != null) {
                return count
            }

            return 0
        }

        fun getCloud(objectId: String): Cloud? {
            return RealmDatabase.shared.db.where(Cloud::class.java).equalTo("objectId", objectId).findFirst()
        }

        fun getCloudData(sortBy: String, ascending: Sort = Sort.ASCENDING): List<Cloud> {
            val results = RealmDatabase.shared.db.where(Cloud::class.java).sort(sortBy, ascending).findAll()
            val listData = RealmQueries.resultsToListConversion(results as RealmResults<RealmObject>)
            return listData as List<Cloud>
        }

        fun getImageURL(objectId: String): URL? {
            val cloud = this.getCloud(objectId)
            return URL(cloud?.detail?.image)
        }

        fun getWikiURL(objectId: String): URL? {
            val cloud = this.getCloud(objectId)
            return URL(cloud?.detail?.wiki)
        }
    }
}