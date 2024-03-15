package com.cs4520.assignment4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DataViewModel(private val productDb: AppDatabase) : ViewModel(){
    var products = MutableLiveData<ProductList?>()

    val productDao = productDb.productDao()


    fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productsResult = ProductApi.retrofitService.getProducts().body()
                products.postValue(productsResult)
                if (productsResult != null) {
                    productDao.deleteAll() // clear the db before we store new data
                    productDao.insertAll(*productsResult.toTypedArray())
                }
            } catch (e: Exception) {
                val storedProducts = productDao.getAll()
                val prodList: ProductList = ProductList()
                for (product in storedProducts) {
                    prodList.add(product)
                }
                products.postValue(prodList)
            }
        }
    }
}