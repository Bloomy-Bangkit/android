package com.dicoding.bloomy.ui.activity.data.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.bloomy.R

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Product> = emptyList()

    fun setData(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.textBesideImage)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.productPrice)

        fun bind(product: Product) {
            productNameTextView.text = product.nama
            productPriceTextView.text = "Price: ${product.price}"
        }
    }
}
