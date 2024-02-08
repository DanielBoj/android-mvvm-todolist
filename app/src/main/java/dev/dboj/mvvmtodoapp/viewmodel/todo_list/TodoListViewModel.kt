package dev.dboj.mvvmtodoapp.viewmodel.todo_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dboj.mvvmtodoapp.MainActivity
import dev.dboj.mvvmtodoapp.R
import dev.dboj.mvvmtodoapp.TodoApp
import dev.dboj.mvvmtodoapp.data.Todo
import dev.dboj.mvvmtodoapp.data.repository.ITodoRepository
import dev.dboj.mvvmtodoapp.util.channel.UiEvent
import dev.dboj.mvvmtodoapp.util.navigation.Routes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val application: Application,
    private val repository: ITodoRepository
) : ViewModel() {

    val todos: Flow<List<Todo>> = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnTodoClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            is TodoListEvent.OnAddTodoClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    insertTodo(todo)
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> deleteTodo(event.todo).also {
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = application.getString(R.string.todo_deleted),
                    action = application.getString(R.string.undo),
                ))
            }
            is TodoListEvent.OnDoneChange -> checkIsDoneEvent(event)
        }
    }

    private fun sendUiEvent(
        event: UiEvent
    ) {
        viewModelScope.launch { _uiEvent.send(event) }
    }

    private fun checkIsDoneEvent(event: TodoListEvent.OnDoneChange) {
        viewModelScope.launch {
            repository.insertTodo(event.todo.copy(
                isDone = event.isDone
            ))
        }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deletedTodo = todo
            repository.deleteTodo(todo)
        }
    }

    private fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }
}