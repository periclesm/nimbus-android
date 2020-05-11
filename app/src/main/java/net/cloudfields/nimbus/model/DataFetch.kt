package net.cloudfields.nimbus.model

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
            this.getDataForClass(ClassName.cloudList)
        }

        private fun getDataForClass(className: ClassName) {
            val query = ParseQuery.getQuery<ParseObject>(className.name)
            query.findInBackground { data, error ->
                if (error != null) {
                    if (error.code == 209) {
                        ParseUser.logOut()
                        ParseController.sharedInstance.anonymousLogin()
                    }
                } else {
                    val mappedData = DataMap.dataMappingForClass(className, data)

                    when (className) {
                        ClassName.cloudList -> {
                            CloudListDAO.listData = mappedData as List<CloudListEntity>
                            Log.d("Data Fetch", "List Data Completed")
                        }

                        ClassName.cloudType -> {
                            CloudTypeDAO.typeData = mappedData as List<CloudTypeEntity>
                            Log.d("Data Fetch", "Type Data Completed")
                        }

                        ClassName.cloudDetail -> {
                            CloudDetailDAO.detailData = mappedData as List<CloudDetailEntity>
                            Log.d("Data Fetch", "Detail Data Completed")
                        }
                    }
                }
            }
        }
    }
}