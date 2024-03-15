package com.cs4520.assignment4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataViewModel : ViewModel(){
    var products = MutableLiveData<ProductList>()
    fun getProducts() {
        viewModelScope.launch {
            val productsResult = ProductApi.retrofitService.getProducts()
            println("Result: " + productsResult.body())
            products.value = productsResult.body()
        }
    }
}