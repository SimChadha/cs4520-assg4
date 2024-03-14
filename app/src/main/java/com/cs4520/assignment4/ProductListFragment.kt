package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.LoginFragmentBinding
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(R.layout.product_list_fragment) {
    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = ProductListFragmentBinding.inflate(inflater, container, false)

        val rvProducts = _binding!!.rvProducts as RecyclerView
        val productList = DatasetImpl().dataset

        // Create adapter using data from our Dataset
        val adapter = ProductListAdapter(productList, container)
        rvProducts.adapter = adapter // Bind our RecycleView to our custom Adapter
        rvProducts.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }
}