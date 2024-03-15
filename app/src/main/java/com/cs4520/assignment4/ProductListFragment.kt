package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(R.layout.product_list_fragment) {
    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private var productList: ProductList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = ProductListFragmentBinding.inflate(inflater, container, false)
        _binding!!.progressBar.visibility = View.VISIBLE // show loading wheel

        val rvProducts = _binding!!.rvProducts as RecyclerView
//        val productList = DatasetImpl().dataset

        val vm = DataViewModel()

        // Create adapter using data from our Dataset
        val adapter = ProductListAdapter(productList, container)
        rvProducts.adapter = adapter // Bind our RecycleView to our custom Adapter
        rvProducts.layoutManager = LinearLayoutManager(this.context)

        vm.products.observe(viewLifecycleOwner, Observer { res ->
            println("Got the following data: ")
            println(res)
            _binding!!.progressBar.visibility = View.GONE // remove loading bar once query is done
            productList = res
            adapter.setProducts(productList)
            adapter.notifyDataSetChanged()
        })
        vm.getProducts()

        return binding.root
    }
}