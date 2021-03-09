package com.tvojaigra.db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object MongoDb {
    private  var kmongo: MongoClient? = null
    private var db: MongoDatabase? = null

    fun getDatabase(): MongoDatabase? {
        if (db == null) {
            connect()
            database()
        }
        return db
    }

    private fun connect() {
        try {
            kmongo = KMongo.createClient(DbConfig.ATLAS_URI)
            println("Connection successful...")
        } catch (e: Exception) {
            kmongo?.close()
            println("Mongo connection failed: $e")
        }
    }
    private fun database() {
        db = kmongo?.getDatabase("tvojaigradb")
    }
}