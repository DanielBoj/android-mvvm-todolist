package dev.dboj.mvvmtodoapp.data.repository

import dev.dboj.mvvmtodoapp.data.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Todo?

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}