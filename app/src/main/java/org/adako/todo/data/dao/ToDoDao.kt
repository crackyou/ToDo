package org.adako.todo.data.dao

import androidx.room.*
import org.adako.todo.data.model.ToDoEntity

@Dao
interface ToDoDao {

    @Insert
    suspend fun insertToDo(todo: ToDoEntity): Long

    @Query("UPDATE ToDo SET is_completed = :status WHERE id = :id")
    suspend fun updateToDo(id: Int, status: Boolean): Int

    @Query("SELECT * FROM ToDo WHERE id =:id")
    suspend fun getToDo(id: Int) : ToDoEntity

    @Query("SELECT * FROM ToDo")
    suspend fun getToDos(): List<ToDoEntity>

    @Delete
    suspend fun removeToDo(entity: ToDoEntity): Int

}