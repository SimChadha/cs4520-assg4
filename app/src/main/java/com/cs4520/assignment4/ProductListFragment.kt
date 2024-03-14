package com.cs4520.assignment4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListFragment : Fragment(R.layout.product_list_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvProducts = view.findViewById<View>(R.id.rvProducts) as RecyclerView
        val productList = DatasetImpl().dataset

        // Create adapter using data from our Dataset
        val adapter = ProductListAdapter(productList)
        rvProducts.adapter = adapter // Bind our RecycleView to our custom Adapter
        rvProducts.layoutManager = LinearLayoutManager(this.context)
    }
}