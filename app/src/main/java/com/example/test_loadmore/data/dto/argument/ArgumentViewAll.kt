package com.example.test_loadmore.data.dto.argument

import com.example.test_loadmore.base.OBase
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ArgumentViewAll(
    @SerializedName("name")
    var name: String): OBase()