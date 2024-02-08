package dev.dboj.mvvmtodoapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dboj.mvvmtodoapp.data.repository.ITodoRepository
import dev.dboj.mvvmtodoapp.data.repository.TodoRepositoryImp
import dev.dboj.mvvmtodoapp.util.connection.TodoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): ITodoRepository {
        return TodoRepositoryImp(db.dao)
    }
}