package com.example.test_loadmore.data.dto.config

import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.dto.categories.Category
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TopResource(
    @SerializedName("id_start")
    var id_start: Int = 0,

    @SerializedName("id_end")
    var id_end: Int = 0,

    @SerializedName("category")
    var category: List<Category> = listOf()
) : OBase()