package br.edu.ifpr.jonatas.task.classes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class ToDoTask(
    var title: String,
    var description: String,
    var state: Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    override fun toString(): String {
        return if(state) {
            "$title - $description [Done]"
        }
        else {
            "$title - $description [Undone]"
        }
    }
}