package dev.dboj.mvvmtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ITodoDao {

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}