package dev.dboj.mvvmtodoapp.data.repository

import dev.dboj.mvvmtodoapp.data.ITodoDao
import dev.dboj.mvvmtodoapp.data.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImp(
    private val dao: ITodoDao
): ITodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id = id)
    }

    override suspend fun insertTodo(todo: Todo) = dao.insertTodo(todo = todo)

    override suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo = todo)
}