package org.adako.todo.ui.activity.add

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.databinding.ActivityAddBinding
import org.adako.todo.ui.base.BaseActivity
import timber.log.Timber

@AndroidEntryPoint
class AddToDoActivity: BaseActivity<AddToDoVM, ActivityAddBinding>() {

    override val viewModel: AddToDoVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initViews()
        iniObservers()
    }

    private fun initViews(){
        binding.appToolbar.title = "Add ToDo"
        setSupportActionBar(binding.appToolbar)

        binding.save.setOnClickListener {
            viewModel.insertNewToDo(ToDoEntity(
                title = binding.todoTitle.text.toString(),
                description = binding.todoDesc.text.toString(),
                isCompleted = false)
            )
        }
    }

    private fun iniObservers(){
        lifecycleScope.launch {
            viewModel.newTodo.collect {
                if (it != null) {
                    Timber.d("ToDo added")
                    finish()
                }
                else
                    Timber.d("Error adding new ToDo")
            }
        }
    }

    override fun getViewBinding(): ActivityAddBinding = ActivityAddBinding.inflate(layoutInflater)
}