package com.example.test_loadmore.data.dto.categories

import com.example.test_loadmore.base.OBase
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryList(
    var list: List<Category> = listOf()) : OBase()