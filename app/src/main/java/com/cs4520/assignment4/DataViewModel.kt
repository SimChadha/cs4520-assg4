package com.cs4520.assignment4

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch
import java.lang.Exception

class DataViewModel(private val productDb: AppDatabase) : ViewModel(){
    var products = MutableLiveData<ProductList?>()

    val productDao = productDb.productDao()


    fun getProducts() {
        viewModelScope.launch {
            try {
                val productsResult = ProductApi.retrofitService.getProducts()
                println("Result: " + productsResult.body())
                products.value = productsResult.body()
                if (products.value != null) {
                    productDao.deleteAll() // clear the db before we store new data
                    //productDao.insertAll(*products.value)
                }
            } catch (e: Exception) {
                println("SHIT BROKE!!!!")
            }
        }
    }
}