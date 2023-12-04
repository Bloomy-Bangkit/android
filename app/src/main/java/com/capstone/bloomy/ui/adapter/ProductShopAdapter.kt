package com.capstone.bloomy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.ItemRowProductShopGridBinding

class ProductShopAdapter(private val product: ArrayList<ProductModel>) : RecyclerView.Adapter<ProductShopAdapter.MyViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class MyViewHolder(var binding: ItemRowProductShopGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowProductShopGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (title, imageUrl, grade, price, rating, sold, _, _) = product[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.imgProductShopGrid)

        holder.binding.tvTitleProductShopGrid.text = title

        holder.binding.tvGradeProductShopGrid.text = grade

        holder.binding.tvPriceProductShopGrid.text = price

        holder.binding.tvRatingProductShopGrid.text = rating

        holder.binding.tvSoldProductShopGrid.text = sold

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(product[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = product.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(product: ProductModel)
    }
}