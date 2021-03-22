package org.adako.todo.ui.activity.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.adako.todo.databinding.ActivityDetailBinding
import org.adako.todo.ui.base.BaseActivity

@AndroidEntryPoint
class DetailActivity: BaseActivity<DetailActivityVM, ActivityDetailBinding>() {


    override val viewModel: DetailActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setSupportActionBar(binding.appToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initObservers()
        val id = intent.getIntExtra("todo",-1)
        if (id >= 0)
            viewModel.getToDo(id)
        else
            finish()
    }

    private fun initObservers(){

        lifecycleScope.launch {
            viewModel.todo.collect{
                if (it != null) {
                    binding.title.text = it.title
                    binding.desc.text = it.description
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId  == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

}