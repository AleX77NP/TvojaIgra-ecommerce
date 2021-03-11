package com.tvojaigra.services

import com.tvojaigra.models.Product
import com.tvojaigra.repositories.ProductRepository

object ProductService {

    fun addProduct(product: Product) {
        ProductRepository.addProduct(product)
    }

    fun getProductById(id:String): Product? {
        return ProductRepository.getProductById(id)
    }

    fun getProductsByCategory(category: String): MutableList<Product>? {
        return ProductRepository.getProductsByCategory(category)
    }

    fun editProduct(id: String, edited: Product) {
        ProductRepository.editProduct(id, edited)
    }

    fun deleteProduct(id: String) {
        ProductRepository.deleteProduct(id)
    }
}