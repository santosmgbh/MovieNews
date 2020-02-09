package com.arctouch.codechallenge.repository.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arctouch.codechallenge.model.Genre

@Dao
interface MovieDao {

    @Query("SELECT * FROM genre")
    fun getGenres(): List<Genre>

    @Insert
    fun insertAll(genres: List<Genre>)
}