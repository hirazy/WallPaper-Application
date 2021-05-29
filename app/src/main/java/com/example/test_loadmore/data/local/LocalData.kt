package com.example.test_loadmore.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.test_loadmore.FAVOURITES_KEY
import com.example.test_loadmore.SHARED_PREFERENCES_APP
import com.example.test_loadmore.data.Resource
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context){
    fun getCachedFavourites(): Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_APP, 0)
        return Resource.Success(sharedPref.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf())
    }

    fun isFavorite(id: String): Resource<Boolean>{
        val sharedPref = context.getSharedPreferences(  SHARED_PREFERENCES_APP, 0)
        val cache = sharedPref.getStringSet(FAVOURITES_KEY, setOf<String>()) ?: setOf()
        return Resource.Success(cache.contains(id))
    }

    fun cacheFavorites(ids: Set<String>): Resource<Boolean>{
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_APP, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

    fun removeFromFavorite(id: String): Resource<Boolean>{
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_APP, 0)
        var set = sharedPref.getStringSet(FAVOURITES_KEY, mutableSetOf<String>())?.toMutableSet() ?: mutableSetOf()
        if (set.contains(id)) {
            set.remove(id)
        }
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        editor.putStringSet(FAVOURITES_KEY, set)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }
}