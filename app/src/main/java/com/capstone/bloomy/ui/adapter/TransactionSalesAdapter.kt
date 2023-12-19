package com.capstone.bloomy.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bloomy.R
import com.capstone.bloomy.data.response.SalesTransactionData
import com.capstone.bloomy.databinding.ItemRowTransactionBinding

class TransactionSalesAdapter : ListAdapter<SalesTransactionData, TransactionSalesAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transaction = getItem(position)

        holder.bind(transaction)
        holder.itemView.setOnClickListener {

        }
    }

    class MyViewHolder(private val binding: ItemRowTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(salesTransactionData: SalesTransactionData) {
            val context = binding.root.context

            Glide.with(binding.imgUserTransaction)
                .load(salesTransactionData.buyerData.pictureBuyer)
                .into(binding.imgUserTransaction)
//
//            Glide.with(binding.imgProductTransaction)
//                .load()
//                .into(binding.imgProductTransaction)

            binding.tvUserTransaction.text = salesTransactionData.buyerData.namaBuyer
            binding.tvStatusTransaction.text = when (salesTransactionData.status) {
                "0" -> context.getString(R.string.not_confirmed)
                "1" -> context.getString(R.string.in_process)
                "2" -> context.getString(R.string.shipped)
                "3" -> context.getString(R.string.finished)
                "4" -> context.getString(R.string.canceled)
                else -> "Unknown"
            }
            binding.tvGradeTransaction.text = salesTransactionData.grade
            binding.tvTitleTransaction.text = "IKAN"
            binding.tvPriceTransaction.text = formatCurrency(salesTransactionData.price) + "/kg"
            binding.tvDeliveryMethodTransaction.text = when (salesTransactionData.type) {
                "0" -> context.getString(R.string.delivery)
                "1" -> context.getString(R.string.self_pickup)
                else -> "Unknown"
            }
            binding.tvQuantityTransaction.text = salesTransactionData.weight.toString()
            binding.tvTotalTransaction.text = "Total: " + formatCurrency(salesTransactionData.totalPrice)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SalesTransactionData>() {
            override fun areItemsTheSame(oldItem: SalesTransactionData, newItem: SalesTransactionData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SalesTransactionData, newItem: SalesTransactionData): Boolean {
                return oldItem == newItem
            }
        }

        fun formatCurrency(amount: Int): String {
            val formattedAmount = String.format("Rp%,d", amount)
            return formattedAmount.replace(',', '.')
        }
    }
}