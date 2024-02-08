package dev.dboj.mvvmtodoapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dboj.mvvmtodoapp.data.Todo
import dev.dboj.mvvmtodoapp.viewmodel.todo_list.TodoListEvent

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { onEvent(TodoListEvent.OnDeleteTodoClick(todo)) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }

            todo.description?.let {description ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 14.sp
                )
            }
        }
    }

    Checkbox(
        checked = todo.isDone,
        onCheckedChange = { isChecked ->
            onEvent(TodoListEvent.OnDoneChange(
                todo = todo,
                isDone = isChecked))
        }
    )
}