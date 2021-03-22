package org.adako.todo.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.adako.todo.data.model.ToDoEntity
import org.adako.todo.data.repositories.ToDoRepository
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val _allTodoList = MutableStateFlow<List<ToDoEntity>>(emptyList())
    val allTodoList: StateFlow<List<ToDoEntity>>
        get() = _allTodoList.asStateFlow()

    private val _changeStatus = MutableSharedFlow<Int>()
    val changeStatus: SharedFlow<Int>
        get() = _changeStatus

    private val _removeTodo = MutableSharedFlow<Int>()
    val removeTodo: SharedFlow<Int>
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