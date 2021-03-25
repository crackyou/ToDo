package org.adako.todo.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.adako.todo.domain.entity.ToDoEntity
import org.adako.todo.databinding.ActivityMainBinding
import org.adako.todo.ui.activity.add.AddToDoActivity
import org.adako.todo.ui.activity.detail.DetailActivity
import org.adako.todo.ui.base.BaseActivity
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityVM, ActivityMainBinding>() {

    override val viewModel: MainActivityVM by viewModels()

    private val data = mutableListOf<ToDoEntity>()

    private lateinit var adapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initViews()
        initObservers()
    }



    private fun initViews() {

        setSupportActionBar(binding.appToolbar)

        adapter = TodoListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("todo", data[it].id)
            startActivity(intent)
        }

        adapter.onStatusClick = {
            Timber.d("onStatusClick: $it")
            viewModel.changeStatus(data[it].id!!,!data[it].isCompleted)
        }

        adapter.onRemoveClick = {
            Timber.d("onRemoveClick: $it")
            viewModel.removeTodo(data[it])
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddToDoActivity::class.java))
        }
    }

    private fun initObservers(){

        lifecycleScope.launchWhenResumed {
            viewModel.allTodoList.collect {
                data.clear()
                data.addAll(it)
                adapter.setData(data)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.changeStatus.collect {
                viewModel.getAllToDos()
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.removeTodo.collect {
                viewModel.getAllToDos()
            }
        }

        viewModel.getAllToDos()
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }


    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}