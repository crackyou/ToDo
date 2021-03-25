package org.adako.todo.domain.repository

import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.domain.dao.ToDoDao
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {

    suspend fun getToDos() = toDoDao.getToDos()

    suspend fun getToDo(id: Int) = toDoDao.getToDo(id)

    suspend fun insertToDo(data: ToDoEntity): Long = toDoDao.insertToDo(data)

    suspend fun updateToDo(id: Int, status: Boolean) = toDoDao.updateToDo(id, status)

    suspend fun removeToDo(data: ToDoEntity) = toDoDao.removeToDo(data)
}