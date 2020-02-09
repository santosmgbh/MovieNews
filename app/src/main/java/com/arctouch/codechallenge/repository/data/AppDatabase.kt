package com.arctouch.codechallenge.repository.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arctouch.codechallenge.model.Genre

@Database(entities = arrayOf(Genre::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}