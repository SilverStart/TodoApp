package com.silverstar.todoapp.ui.input

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.view.clicks
import com.silverstar.todoapp.R
import com.silverstar.todoapp.util.Event
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class TodoInputActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()

    private lateinit var progressBar: View
    private lateinit var contentContainer: View
    private lateinit var title: EditText
    private lateinit var content: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_input)

        progressBar = findViewById(R.id.progress_bar)
        contentContainer = findViewById(R.id.content_container)
        title = findViewById(R.id.et_title)
        content = findViewById(R.id.et_content)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(TodoInputViewModel::class.java)

        viewModel.processIntents(getIntents())

        compositeDisposable.add(viewModel.states.subscribe {
            updateLoading(it.isLoading)
            updateSuccess(it.isSuccess)
            updateError(it.error)
        })
    }

    private fun getIntents(): Observable<TodoInputIntent> {
        return findViewById<Button>(R.id.btn_save)
            .clicks()
            .map {
                TodoInputIntent.SaveIntent(title.text.toString(), content.text.toString())
            }
    }

    private fun updateLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        contentContainer.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun updateSuccess(isSuccess: Event<Boolean>) {
        if (isSuccess.isHandled) return

        if (isSuccess.getIfNotHandled() == true) {
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun updateError(error: Event<TodoInputError>) {
        if (error.isHandled) return

        when (error.getIfNotHandled()) {
            TodoInputError.NONE -> {

            }
            TodoInputError.EMPTY_TITLE -> {
                Toast.makeText(this, "제목이 비어있습니다.", Toast.LENGTH_LONG).show()
            }
            TodoInputError.FAILED_TO_SAVE -> {
                Toast.makeText(this, "저장에 실패하였습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
