package net.cloudfields.nimbus.model

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
            this.getDataForClass(ClassName.cloudList)
            //this.getDataForClass(ClassName.Type)
            //this.getDataForClass(ClassName.Detail)
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

                    // do any process here

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
                }
            }
        }
    }
}