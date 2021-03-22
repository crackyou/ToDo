package org.adako.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.adako.domain.entities.ToDoEntity

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(loginTableModel: ToDoEntity)

    @Query("SELECT * FROM ToDo WHERE id =:id")
    suspend fun getToDo(id: Int) : ToDoEntity

    @Query("SELECT * FROM ToDo")
    suspend fun getToDos(): List<ToDoEntity>
}