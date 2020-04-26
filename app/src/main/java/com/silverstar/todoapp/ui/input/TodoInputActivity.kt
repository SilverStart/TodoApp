package com.silverstar.todoapp.ui.input

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.silverstar.todoapp.R
import com.silverstar.todoapp.business.entity.Todo
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class TodoInputActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(TodoInputViewModel::class.java)
    }

}
