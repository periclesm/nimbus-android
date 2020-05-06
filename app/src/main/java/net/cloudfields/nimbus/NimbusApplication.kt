package net.cloudfields.nimbus

import android.app.Application
import android.content.Context
import android.content.res.Resources
import net.cloudfields.nimbus.controller.ParseController

class NimbusApplication : Application() {
    companion object {
        private var application: Application? = null

        fun getApplication(): Application? {
            return application
        }

        fun getContext(): Context? {
            return getApplication()?.applicationContext
        }

        fun appResources(): Resources {
            return this.getContext()!!.resources
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        this.appPreferences()

        ParseController.sharedInstance.initParse()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun appPreferences() {
        //just add any preference here when needed
    }
}