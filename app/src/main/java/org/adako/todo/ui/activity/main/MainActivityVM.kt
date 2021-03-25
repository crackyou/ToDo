package org.adako.todo.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.domain.repository.ToDoRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val _allTodoList = MutableStateFlow<List<ToDoEntity>>(emptyList())
    val allTodoList: StateFlow<List<ToDoEntity>>
        get() = _allTodoList.asStateFlow()

    private val _changeStatus = MutableSharedFlow<Long>()
    val changeStatus: SharedFlow<Long>
        get() = _changeStatus

    private val _removeTodo = MutableSharedFlow<Long>()
    val removeTodo: SharedFlow<Long>
        get() = _removeTodo

    fun getAllToDos() {
        viewModelScope.launch(Dispatchers.IO) {
            _allTodoList.value = toDoRepository.getToDos()
        }
    }

    fun changeStatus(id: Int, status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _changeStatus.emit(toDoRepository.updateToDo(id, status))
        }
    }

    fun removeTodo(todo: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            _removeTodo.emit(toDoRepository.removeToDo(todo))
        }
    }
}