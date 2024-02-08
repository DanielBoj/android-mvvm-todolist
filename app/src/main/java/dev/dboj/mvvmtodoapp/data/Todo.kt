package dev.dboj.mvvmtodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
