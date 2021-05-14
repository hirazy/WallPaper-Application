package com.example.test_loadmore.data.local.room

import androidx.room.*
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.dto.image.Image
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Dao
interface DaoImage {
    @Insert
    fun insert(image: Image)

    @Update
    fun update(image: Image)

    @Delete
    fun delete(image: Image)

    @Query("delete from image")
    fun deleteImage()

    @Query("select * from image")
    fun getAllFiles(): List<Image>
}