package org.adako.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.adako.todo.data.model.ToDoEntity
import org.adako.todo.data.dao.ToDoDao

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao
}