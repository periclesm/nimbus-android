package net.cloudfields.nimbus.model

import android.os.Handler
import android.util.Log
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import net.cloudfields.nimbus.ClassName
import net.cloudfields.nimbus.controller.ParseController
import net.cloudfields.nimbus.model.dao.CloudDetailDAO
import net.cloudfields.nimbus.model.dao.CloudListDAO
import net.cloudfields.nimbus.model.dao.CloudTypeDAO
import net.cloudfields.nimbus.model.entity.CloudDetailEntity
import net.cloudfields.nimbus.model.entity.CloudListEntity
import net.cloudfields.nimbus.model.entity.CloudTypeEntity

class DataFetch {
    companion object {

        fun getData() {
            this.getDataForClass(ClassName.cloudType)
            this.getDataForClass(ClassName.cloudDetail)

            Handler().postDelayed ({
                this.getDataForClass(ClassName.cloudList)
            }, 1500)
        }

        private fun getDataForClass(className: ClassName) {
            Log.d("[Nimbus] Data", "${className.name} data start")
            val query = ParseQuery.getQuery<ParseObject>(className.name)
            query.findInBackground { data, error ->
                if (error != null) {
                    if (error.code == 209) {
                        Log.d("[Nimbus] Session Expired", "While fetching ${className.name} data")
                        ParseUser.logOut()
                        ParseController.sharedInstance.anonymousLogin()
                    }
                } else {
                    val mappedData = DataMap.dataMappingForClass(className, data)

                    when (className) {
                        ClassName.cloudList -> {
                            CloudListDAO.listData = mappedData as List<CloudListEntity>
                        }

                        ClassName.cloudType -> {
                            CloudTypeDAO.typeData = mappedData as List<CloudTypeEntity>
                        }

                        ClassName.cloudDetail -> {
                            CloudDetailDAO.detailData = mappedData as List<CloudDetailEntity>
                        }
                    }

                    Log.d("[Nimbus] Data", "${className.name} data complete")
                }
            }
        }
    }
}

private fun Handler.postDelayed(function: () -> Unit) {

}
