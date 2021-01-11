package net.cloudfields.nimbus.model.realmmanager

import io.realm.*

class RealmOperation {
    companion object {

        fun get(rObject: RealmObject): RealmResults<out RealmObject>? {
            val db = RealmDatabase.shared.db
            return db.where(rObject::class.java).findAll()
        }

        /// Adds a single object in the database
        fun add(rObject: RealmObject, async: Boolean = false) {
            val db = RealmDatabase.shared.db

            if (async) {
                db.executeTransactionAsync({
                    db.insertOrUpdate(rObject)
                }, { error ->
                    //do something to handle this
                })
            } else {
                db.executeTransaction {
                    db.insertOrUpdate(rObject)
                }
            }
        }

        /// Adds an array of objects in the database
        fun add(objectList: List<RealmObject>, async: Boolean = false) {
            val db = RealmDatabase.shared.db

            if (async) {
                db.executeTransactionAsync({
                    db.insertOrUpdate(objectList)
                }, { error ->
                    //handle this
                })
            } else {
                db.executeTransaction {
                    db.insertOrUpdate(objectList)
                }
            }
        }

        /// Delete an object from the database
        fun delete(rObject: RealmObject, async: Boolean = false) {
            val db = RealmDatabase.shared.db

            if (async) {
                db.executeTransactionAsync({
                    rObject.deleteFromRealm()
                }, { error ->
                    // handle it
                })
            } else {
                db.executeTransaction {
                    rObject.deleteFromRealm()
                }
            }
        }

        /// Delete an array of objects from the database
        fun delete(objectList: RealmList<RealmObject>, async: Boolean = false) {
            val db = RealmDatabase.shared.db

            if (async) {
                db.executeTransactionAsync({
                    objectList.deleteAllFromRealm()
                }, { error ->
                    // handle it
                })
            } else {
                db.executeTransaction {
                    objectList.deleteAllFromRealm()
                }
            }
        }

        fun deleteAll(rObject: RealmObject, async: Boolean = false) {
            val db = RealmDatabase.shared.db

            if (async) {
                db.executeTransactionAsync({
                    val entries = this.get(rObject)
                    entries?.deleteAllFromRealm()
                }, { error ->
                    // handle it
                })
            } else {
                db.executeTransaction {
                    val entries = this.get(rObject)
                    entries?.deleteAllFromRealm()
                }
            }
        }

        /// Delete everything from the database
        fun deleteAllData() {
            val db = RealmDatabase.shared.db

            db.executeTransactionAsync {
                db.deleteAll()
            }
        }
    }
}