package com.example.storeroom.data.link

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLinkInfo(
    val url: String = "",
    val category: String = "",
    val note: String = "",
    val isFavorite: Boolean = false,
    val linkId: String ="",
): Parcelable
