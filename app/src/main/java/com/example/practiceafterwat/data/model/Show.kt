package com.example.practiceafterwat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Show(
    val id: String,
    val name: String,
    val image: Image,
    val summary: String
) : Parcelable

@Parcelize
data class Image(
    val medium: String,
    val original: String
) : Parcelable
