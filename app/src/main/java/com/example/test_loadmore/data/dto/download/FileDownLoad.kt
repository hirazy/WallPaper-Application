package com.example.test_loadmore.data.dto.download

import android.os.Parcelable
import com.example.test_loadmore.base.OBase
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FileDownLoad (
        @SerializedName("title")
        var title: String,
        @SerializedName("md5")
        var md5: String,
        @SerializedName("url")
        var url: String): OBase(), Parcelable