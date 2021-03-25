package org.adako.todo.ui.activity.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.domain.repository.ToDoRepository
import javax.inject.Inject

@HiltViewModel
class DetailActivityVM @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    private val _todo = MutableStateFlow<ToDoEntity?>(null)
    val todo: StateFlow<ToDoEntity?>
        get() = _todo

    fun getToDo(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _todo.value = toDoRepository.getToDo(id)
        }
    }
}