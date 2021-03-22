package org.adako.domain.repositories

import org.adako.domain.entities.ToDoEntity
import org.adako.domain.room.ToDoDao
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {

    suspend fun getToDos() = toDoDao.getToDos()

    suspend fun getToDo(id: Int) = toDoDao.getToDo(id)

    suspend fun insertToDo(data: ToDoEntity) = toDoDao.insertToDo(data)
}