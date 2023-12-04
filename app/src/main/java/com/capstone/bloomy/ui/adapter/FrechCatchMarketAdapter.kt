package com.capstone.bloomy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.ItemRowFreshCatchMarketBinding

class FreshCatchMarketAdapter(private val freshCatchMarket: ArrayList<ProductModel>) : RecyclerView.Adapter<FreshCatchMarketAdapter.MyViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class MyViewHolder(var binding: ItemRowFreshCatchMarketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowFreshCatchMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (title, imageUrl, grade, price, rating, sold, location, _) = freshCatchMarket[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.imgFreshCatchMarket)

        holder.binding.tvTitleFreshCatchMarket.text = title

        holder.binding.tvGradeFreshCatchMarket.text = grade

        holder.binding.tvPriceFreshCatchMarket.text = price

        holder.binding.tvRatingFreshCatchMarket.text = rating

        holder.binding.tvSoldFreshCatchMarket.text = sold

        holder.binding.tvLocationFreshCatchMarket.text = location

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(freshCatchMarket[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = freshCatchMarket.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(freshCatchMarket: ProductModel)
    }
}