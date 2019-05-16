package br.edu.ifpr.jonatas.todolist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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
    @ColumnInfo(name = "id")
    var localId: Long = 0
    @SerializedName("id")
    @ColumnInfo(name = "remote_id")
    var remoteId : Long? = null
    var localStatus : Int? = null

    companion object {
        val STATUS_INSERTED = 0
        val STATUS_UPDATED = 1
        val STATUS_REMOVED = 2
    }

    override fun equals(other: Any?) =
        other != null
                && (this === other
                || (this.remoteId != null && this.remoteId == (other as Task).remoteId)
                || (this.localId != 0L && this.localId == (other as Task).localId))
}