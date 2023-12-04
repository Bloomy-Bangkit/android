package com.capstone.bloomy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.ItemRowMarketGradeProductBinding

class MarketGradeProductAdapter(private val marketGradeProduct: ArrayList<ProductModel>) : RecyclerView.Adapter<MarketGradeProductAdapter.MyViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class MyViewHolder(var binding: ItemRowMarketGradeProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowMarketGradeProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (title, imageUrl, _, price, rating, sold, location, _) = marketGradeProduct[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.imgMarketGradeProduct)

        holder.binding.tvTitleMarketGradeProduct.text = title

        holder.binding.tvPriceMarketGradeProduct.text = price

        holder.binding.tvRatingMarketGradeProduct.text = rating

        holder.binding.tvSoldMarketGradeProduct.text = sold

        holder.binding.tvLocationMarketGradeProduct.text = location

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(marketGradeProduct[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = marketGradeProduct.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(marketGradeProduct: ProductModel)
    }
}