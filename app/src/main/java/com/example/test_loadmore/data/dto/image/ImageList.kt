package com.example.test_loadmore.data.dto.image

import com.example.test_loadmore.base.OBase
import kotlinx.android.parcel.Parcelize

@Parcelize
class ImageList (
    var list: List<Image> = listOf()
): OBase()