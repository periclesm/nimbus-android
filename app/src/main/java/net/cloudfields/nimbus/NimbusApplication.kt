package net.cloudfields.nimbus

import android.app.Application
import android.content.Context
import android.content.res.Resources
import net.cloudfields.nimbus.model.DataManager
import net.cloudfields.nimbus.model.realmmanager.RealmDatabase

class NimbusApplication : Application() {
    companion object {
        private var application: Application? = null

        fun getApplication(): Application? {
            return this.application
        }

        fun getContext(): Context? {
            return this.application?.applicationContext
        }

        fun appResources(): Resources? {
            return this.application?.applicationContext?.resources
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        RealmDatabase.shared.initDatabase()
        DataManager.prefetchData {}
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}