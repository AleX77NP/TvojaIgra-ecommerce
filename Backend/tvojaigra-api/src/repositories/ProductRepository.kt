package com.tvojaigra.repositories

import com.tvojaigra.db.MongoDb
import com.tvojaigra.models.Product
import org.litote.kmongo.*

object ProductRepository {

    val productCol = MongoDb.getDatabase()?.getCollection<Product>()

    fun addProduct(product: Product) {
        productCol?.insertOne(product)
    }

    fun getProductById(id: String): Product? {
        return productCol?.findOneById(id)
    }

    fun getProductsByCategory(category: String): MutableList<Product>? {
        return productCol?.find(Product::category eq category)?.toMutableList()
    }

    fun editProduct(id: String, edited: Product) {
        productCol?.updateOne(Product::_id eq id, set(Product::title setTo edited.title, Product::description setTo edited.description,
        Product::brand setTo edited.brand, Product::image setTo edited.image, Product::category setTo edited.category,
        Product::available setTo edited.available))
    }

    fun deleteProduct(id: String) {
        productCol?.findOneAndDelete(Product::_id eq id)
    }

}