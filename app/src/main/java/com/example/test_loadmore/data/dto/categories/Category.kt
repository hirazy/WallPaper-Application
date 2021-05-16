package com.example.test_loadmore.data.dto.categories

import com.example.test_loadmore.base.OBase
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category(
    @SerializedName("name")
    var name: String = "",

    @SerializedName("image1")
    var image1: String = "",

    @SerializedName("image2")
    var image2: String = "",

    @SerializedName("image3")
    var image3: String = "",

    @SerializedName("image4")
    var image4: String = ""
) : OBase()