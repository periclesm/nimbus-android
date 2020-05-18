package net.cloudfields.nimbus.controller

import com.parse.Parse
import com.parse.ParseAnonymousUtils
import net.cloudfields.nimbus.NimbusApplication
import net.cloudfields.nimbus.model.DataFetch

class ParseController {
    companion object {
        var sharedInstance = ParseController()
    }

    fun initParse() {
        val appContext = NimbusApplication.getContext()
        val config = Parse.Configuration.Builder(appContext!!)
            .applicationId("Y6372R67Q2UCqCsWuCnD4YZ3ACEiiFxngF6WNqwE")
            .clientKey("LdJJnrhcCEiY8kRA3vAwg4sxT73LhQu84Efmv796")
            .server("https://stage.clfd.eu/nimbus")
            .build()

        Parse.initialize(config)
        //this.anonymousLogin()
        DataFetch.getData()
    }

    fun anonymousLogin() {
        ParseAnonymousUtils.logIn { user, error ->
            if (error != null) {

            } else {
                DataFetch.getData()
            }
        }
    }
}