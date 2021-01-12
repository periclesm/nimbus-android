package net.cloudfields.nimbus.model.realmmanager

import io.realm.*

class RealmQueries {
    companion object {

        //MARK: - Composite Sort and Filter --

        /// Under Development
        fun filterAndSort(rObject: RealmObject, sortAttribute: String, ascending: Sort = Sort.ASCENDING): RealmResults<out RealmObject>? {
            return null
        }

        /// Under Development
        fun filterAndSort(dataList: RealmResults<RealmObject>, keypath: String, ascending: Sort = Sort.ASCENDING): RealmResults<out RealmObject>? {
            return null
        }

        //MARK: - Filter

        /// Under Development
        fun filter(dataObject: RealmObject): RealmResults<RealmObject>? {
            return null
        }

        /// Under Development
        fun filter(dataList: RealmResults<RealmObject>): RealmResults<RealmObject>? {
            return null
        }

        //MARK: - Sort

        /**
         * Retrieves the requested Realm objects and sorts them by the input attributes.
         * @param rObject: The Realm object.
         * @param sortAttribute: `String` The attribute name to be used for sorting the data.
         * @param ascending: `Boolean` The sort type: `true` for Ascending, `false` for Descending. Default is true.
         * @return A Realm `Results` collection with data of the requested query.
         */
        fun sort(rObject: RealmObject, sortAttribute: String, ascending: Sort = Sort.ASCENDING): RealmResults<out RealmObject>? {
            return RealmOperation.get(rObject)?.sort(sortAttribute, ascending)
        }

        /**
        Sorts the input Realm `Results` by the input attributes.
         * @note Does not query the database. Uses `objectResults` instead.
         * @param objectResults: The Realm `Results` (used as input)
         * @param sortAttribute: `String` The attribute name to be used for sorting the data.
         * @param ascending: `Boolean` The sort type: `true` for Ascending, `false` for Descending. Default is true.
         * @return A Realm `Results` collection with data of the requested query.
         */
        fun sort(objectList: RealmResults<RealmObject>, sortAttribute: String, ascending: Sort = Sort.ASCENDING): RealmResults<RealmObject> {
            return objectList.sort(sortAttribute, ascending)
        }

        /**
        Sorts the input Realm `Results` by the input attributes.
         * @note Does not query the database. Uses `objectResults` instead.
         * @param objectList: The Realm `List` (used as input)
         * @param sortAttribute: `String` The attribute name to be used for sorting the data.
         * @param ascending: `Boolean` The sort type: `true` for Ascending, `false` for Descending. Default is true.
         * @return A Realm `List` collection with data of the requested query.
         */
        fun sort(objectList: RealmList<RealmObject>, sortAttribute: String, ascending: Sort = Sort.ASCENDING): RealmList<RealmObject> {
            val results = objectList.sort(sortAttribute, ascending)
            return this.resultsToRealmListConversion(results)
        }

        //MARK: - Limit

        /**
         * Limits a given Array to the (input) number of desired objects.
         *  @param objectArray: An `Array`of objects (used as input).
         *  @param limit: `Int` Limits the number of items returned. Only the top `limit` numbers are included.
         *  @return A subarray with the top objects.
         */
        fun limit(datalist: List<Any>, limit: Int): List<Any> {
            return if (limit < datalist.count()) {
                datalist.subList(fromIndex = 0, toIndex = limit)
            } else {
                datalist
            }
        }

        //MARK: - Utilities

        /**
         * Converts an input Realm `Results` collection to a Realm `List`.
         */
        fun resultsToRealmListConversion(results: RealmResults<RealmObject>?): RealmList<RealmObject> {
            if (results != null) {
                if (results.isNotEmpty()) {
                    val resultsList: RealmList<RealmObject> = RealmList()

                    for (rObject in results) {
                        resultsList.add(rObject)
                    }

                    return resultsList
                }
            }

            return RealmList()
        }

        fun resultsToListConversion(results: RealmResults<RealmObject>?): List<RealmObject> {
            if (results != null) {
                if (results.isNotEmpty()) {
                    val resultsList: MutableList<RealmObject> = mutableListOf()

                    for (rObject in results) {
                        resultsList.add(rObject)
                    }

                    return resultsList
                }
            }

            return listOf()
        }
    }
}