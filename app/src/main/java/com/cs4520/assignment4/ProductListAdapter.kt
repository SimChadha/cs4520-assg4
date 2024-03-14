package com.cs4520.assignment4

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductListItemBinding

class ProductListAdapter(private val products: List<List<Any?>>, private val container: ViewGroup?) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    // Helper subclass that defines our View for each row of the table
    class ViewHolder(binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemNameView: TextView
        val itemPriceView: TextView
        val imageView: ImageView
        val expiryView: TextView

        init {
            itemNameView = binding.productName
            itemPriceView = binding.productPrice
            imageView = binding.productImage
            expiryView = binding.productExpiry
        }
    }

    // Inflate the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val productListBinding: ProductListItemBinding =
            ProductListItemBinding.inflate(inflater, container, false)
        return ViewHolder(productListBinding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // If data is malformed and we get a null price or name, display 'Bad Data'
        // Otherwise, populate view with data from passed in products
        holder.itemNameView.text = (products[position][0] ?: "Bad Data").toString()
        holder.itemPriceView.text = "$" + (products[position][3] ?: "Bad Data").toString()

        if (products[position][2] == null) {
            // Hide expiry view if there is no expiry data
            holder.expiryView.visibility = View.GONE
        }
        else {
            holder.expiryView.visibility = View.VISIBLE
            // Otherwise, display the expiry
            holder.expiryView.text = products[position][2].toString()
        }
        
        val type = products[position][1].toString()
        val image: Int

        if (type == "Food") {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFD965"))
            image = R.drawable.food
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#E06666"))
            image = R.drawable.equipment
        }
        holder.imageView.setImageResource(image)
    }
}

