package com.example.test_loadmore.data.dto.categories.top

import com.example.test_loadmore.base.OBase
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryTopList(
    var list: List<CategoryTop> = listOf()
): OBase()