package com.cs4520.assignment4


class ProductList: ArrayList<Product>()

data class Product(val name: String, val type: String, val expiryDate: String?, val price: Double) {

}

//data class Equipment(val name: String, val price: Int): Product()
//data class Food(val name: String, val expiry: String, val price: Int): Product()