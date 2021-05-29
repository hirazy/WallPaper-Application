package com.example.test_loadmore.data.local.room

import androidx.room.*
import com.example.test_loadmore.data.dto.image.Image


@Dao
interface DaoImage {
    @Insert
    fun insert(image: Image)

    @Update
    fun update(image: Image)

    @Delete
    fun delete(image: Image)

    @Query("SELECT * FROM image WHERE id =:id")
    fun deleteImageById(id: Int): Image

    @Query("select * from image")
    fun getAllImages(): List<Image>
}