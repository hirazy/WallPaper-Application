package com.example.test_loadmore.data.dto.image

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_loadmore.base.OBase
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "image")
@Parcelize
data class Image (
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("type")
    var type: Int = 0
): OBase(){

    @PrimaryKey
    var uid: Long = 0
}