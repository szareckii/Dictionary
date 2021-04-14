package com.szareckii.dictionary.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class Translation(
//    @Expose val text: String?
    @field:SerializedName("text") val translation: String?
)
