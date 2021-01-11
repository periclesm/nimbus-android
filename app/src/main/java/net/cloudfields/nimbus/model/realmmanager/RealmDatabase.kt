package net.cloudfields.nimbus.model.realmmanager

import io.realm.*
import net.cloudfields.nimbus.NimbusApplication

class RealmDatabase {
    companion object {
        var shared = RealmDatabase()
    }

    private val schemaVer: Long = 0
    private val dbFileName: String = "cloudDB.realm"
    private var mainDB: Realm? = null

    var db: Realm
        set(newSchema) {
            this.mainDB = newSchema
        }
        get() {
            if (this.mainDB == null) {
                this.mainDB = this.initDB()
            }

            return this.mainDB!!
        }

    ///Initializes the database instance to be used in the app
    fun initDB(): Realm {
        Realm.init(NimbusApplication.getContext()!!)

        val config = RealmConfiguration.Builder()
            .name(dbFileName)
            .schemaVersion(schemaVer)
            .deleteRealmIfMigrationNeeded()
            .compactOnLaunch()
            .build()

        return Realm.getInstance(config)
    }
}