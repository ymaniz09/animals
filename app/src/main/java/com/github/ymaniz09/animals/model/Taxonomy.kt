package com.github.ymaniz09.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Taxonomy(
    val kingdom: String?,
    val order: String?,
    val family: String?
) : Parcelable