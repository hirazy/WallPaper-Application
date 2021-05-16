package com.example.test_loadmore.data.dto.categories

import com.example.test_loadmore.base.OBase
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryL(
    @SerializedName("name")
    var name: String = "",

    @SerializedName("id_start")
    var id_start: Int = 0,

    @SerializedName("id_end")
    var id_end : Int = 0
): OBase()