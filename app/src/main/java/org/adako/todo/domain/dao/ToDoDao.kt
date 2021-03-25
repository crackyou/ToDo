package org.adako.todo.domain.dao

import androidx.room.*
import org.adako.todo.domain.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDo")
    suspend fun getToDos(): List<ToDoEntity>

    @Query("SELECT * FROM ToDo WHERE id =:id")
    suspend fun getToDo(id: Int) : ToDoEntity

    @Insert
    suspend fun insertToDo(todo: ToDoEntity): Long

    @Query("UPDATE ToDo SET is_completed = :status WHERE id = :id")
    suspend fun updateToDo(id: Int, status: Boolean): Long

    @Delete
    suspend fun removeToDo(entity: ToDoEntity): Long

}