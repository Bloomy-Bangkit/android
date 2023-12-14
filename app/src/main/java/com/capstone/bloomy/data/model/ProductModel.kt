package com.capstone.bloomy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel (

    val title: String,

    val imageUrl: String,

    val grade: String,

    val price: String,

    val rating: String,

    val sold: String,

    val location: String,

    val isFavorite: String

) : Parcelable