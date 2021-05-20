package com.example.test_loadmore.data.dto.argument

import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.config.TopResource
import kotlinx.android.parcel.Parcelize


@Parcelize
class ArgumentRequestNetwork(

    var top: TopResource ,

    var _4d: PopularResource,

    var _4k: PopularResource,

    var live: PopularResource,

    var category: List<Category> = listOf()
): OBase()