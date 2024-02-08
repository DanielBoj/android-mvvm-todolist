package dev.dboj.mvvmtodoapp.util.connection

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dboj.mvvmtodoapp.data.ITodoDao
import dev.dboj.mvvmtodoapp.data.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: ITodoDao
}