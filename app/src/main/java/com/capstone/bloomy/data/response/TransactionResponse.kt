package com.capstone.bloomy.data.response

import com.google.gson.annotations.SerializedName

data class PurchasesTransactionResponse(

    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val purchasesTransactionData: List<PurchasesTransactionData>
)

data class PurchasesTransactionData(

    @SerializedName("idTransaction")
    val idTransaction: String,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("price")
    val price: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("noResi")
    val noResi: String,

    @SerializedName("ongkir")
    val ongkir: Int,

    @SerializedName("totalPrice")
    val totalPrice: Int,

    @SerializedName("datePickup")
    val datePickup: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("product")
    val productPurchaseData: ProductPurchaseData,

    @SerializedName("seller")
    val sellerData: SellerData
)

data class ProductPurchaseData(

    @SerializedName("idProduct")
    val idProduct: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("grade")
    val grade: String,

    @SerializedName("picture")
    val picture: String,

    @SerializedName("pricePerKg")
    val pricePerKg: String
)

data class SellerData(

    @SerializedName("usernameSeller")
    val usernameSeller: String,

    @SerializedName("namaSeller")
    val nameSeller: String,

    @SerializedName("picture")
    val picture: String
)

data class SalesTransactionResponse(

    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val salesTransactionData: List<SalesTransactionData>
)

data class SalesTransactionData(

    @SerializedName("idTransaction")
    val idTransaction: String,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("price")
    val price: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("noResi")
    val noResi: String,

    @SerializedName("ongkir")
    val ongkir: Int,

    @SerializedName("totalPrice")
    val totalPrice: Int,

    @SerializedName("datePickup")
    val datePickup: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("product")
    val productSaleData: ProductSaleData,

    @SerializedName("buyer")
    val buyerData: BuyerData
)

data class ProductSaleData(

    @SerializedName("idProduct")
    val idProduct: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("grade")
    val grade: String,

    @SerializedName("picture")
    val picture: String,

    @SerializedName("pricePerKg")
    val pricePerKg: String
)

data class BuyerData(

    @SerializedName("usernameBuyer")
    val usernameBuyer: String,

    @SerializedName("namaBuyer")
    val namaBuyer: String,

    @SerializedName("pictureBuyer")
    val pictureBuyer: String
)

data class BuyProductResponse(

    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val buyProductData: BuyProductData
)

data class BuyProductData(

    @SerializedName("noResi")
    val noResi: String,

    @SerializedName("ongkir")
    val ongkir: Int,

    @SerializedName("idTransaction")
    val idTransaction: String,

    @SerializedName("idProduct")
    val idProduct: String,

    @SerializedName("usernameBuyer")
    val usernameBuyer: String,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("price")
    val price: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("datePickup")
    val datePickup: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)
