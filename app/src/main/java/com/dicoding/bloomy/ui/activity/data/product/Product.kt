package com.dicoding.bloomy.ui.activity.data.product

data class Product(
    val idProduct: String,
    val usernameSeller: String,
    val picture: String,
    val nama: String,
    val description: String,
    val grade: String,
    val price: Int,
    val weight: Int,
    val createdAt: String,
    val updatedAt: String,
    val favorite: Boolean
)

data class ApiResponse(
    val error: Boolean,
    val message: String,
    val data: List<Product>?
)
