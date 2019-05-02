package br.edu.ifpr.jonatas.todolist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "tasks")
data class Task(
    var title: String,
    var description: String,
    var done: Boolean
//    var created_at: Timestamp,
//    var updated_at: Timestamp,
//    var url : String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun equals(other: Any?) =
        other != null && (this === other || (this.id != 0L && this.id == (other as Task).id))
}