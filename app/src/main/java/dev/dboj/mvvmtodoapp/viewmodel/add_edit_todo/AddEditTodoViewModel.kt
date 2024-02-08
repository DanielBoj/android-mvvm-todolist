package dev.dboj.mvvmtodoapp.viewmodel.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dboj.mvvmtodoapp.data.Todo
import dev.dboj.mvvmtodoapp.data.repository.ITodoRepository
import dev.dboj.mvvmtodoapp.util.channel.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: ITodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId") ?: -1
        if (todoId != -1) getTodoById(todoId)
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChange -> title = event.title
            is AddEditTodoEvent.OnDescriptionChange -> description = event.description
            is AddEditTodoEvent.OnSaveTodoClick -> saveTodo()
        }
    }

    private fun getTodoById(todoId: Int) {
        viewModelScope.launch {
              repository.getTodoById(todoId)?.let {todo ->
                  this@AddEditTodoViewModel.todo = todo
                  setTitleAndDescription(todo)
              }
        }
    }

    private fun setTitleAndDescription(todo: Todo) {
        title = todo.title
        description = todo.description ?: ""
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if (title.isNotBlank()) {
                repository.insertTodo(
                    Todo(
                        title = title,
                        description = description,
                        isDone = todo?.isDone ?: false,
                        id = todo?.id
                    )
                )
                sendUiEvent(UiEvent.PopBackStack)
            }

            sendUiEvent(UiEvent.ShowSnackBar(
                message = "Title cannot be empty"
            ))
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}