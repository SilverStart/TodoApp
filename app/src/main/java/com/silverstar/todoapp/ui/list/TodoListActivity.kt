package com.silverstar.todoapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.silverstar.todoapp.R
import com.silverstar.todoapp.data.entity.Todo
import com.silverstar.todoapp.ui.input.TodoInputActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class TodoListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TodoListViewModel::class.java)

        viewModel.states.subscribe(::updateState)

        val todoInput = findViewById<Button>(R.id.btn_todo_input)

        todoInput.setOnClickListener {
            startActivity(Intent(this, TodoInputActivity::class.java))
        }
    }

    private fun updateState(state: TodoListViewState) {
        updateLoading(state.isLoading)
        updateTodoList(state.todoList)
    }

    private fun updateLoading(isLoading: Boolean) {

    }

    private fun updateTodoList(todoList: List<Todo>) {

    }
}
