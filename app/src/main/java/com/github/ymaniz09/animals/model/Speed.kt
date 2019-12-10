package com.github.ymaniz09.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Speed(
    val metric: String?,
    val imperial: String?
) : Parcelable