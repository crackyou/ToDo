package org.adako.todo.domain.usecases

import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
){
    suspend fun getToDos(): List<ToDoEntity> = repository.getToDos()

    suspend fun getToDo(id: Int): ToDoEntity = repository.getToDo(id)

    suspend fun insertToDo(data: ToDoEntity): Long = repository.insertToDo(data)

    suspend fun updateToDo(id: Int, status: Boolean): Long = repository.updateToDo(id, status)

    suspend fun removeToDo(data: ToDoEntity): Long = repository.removeToDo(data)
}