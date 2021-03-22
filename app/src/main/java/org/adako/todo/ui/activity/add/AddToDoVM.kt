package org.adako.todo.ui.activity.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.adako.todo.data.model.ToDoEntity
import org.adako.todo.data.repositories.ToDoRepository
import javax.inject.Inject

@HiltViewModel
class AddToDoVM @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    private val _newTodo = MutableStateFlow<Long?>(null)
    val newTodo: StateFlow<Long?> = _newTodo

    fun insertNewToDo(todo: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO){
            _newTodo.value = toDoRepository.insertToDo(todo)
        }
    }
}