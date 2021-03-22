package org.adako.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.adako.domain.entities.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao
}