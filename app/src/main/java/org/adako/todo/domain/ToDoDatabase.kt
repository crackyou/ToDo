package org.adako.todo.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.domain.dao.ToDoDao

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao
}