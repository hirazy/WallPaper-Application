package com.example.test_loadmore.data.dto

import android.os.Parcelable
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.dto.download.FileDownLoad
import com.example.test_loadmore.data.dto.image.Image
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Addon(
    @SerializedName("downloads")
    var downloads: List<FileDownLoad> = listOf(),
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("images")
    var images: List<Image>,
    @SerializedName("text")
    var text: String,
    @SerializedName("thumb")
    var thumb: String
): OBase(), Parcelable