package br.edu.ifpr.jonatas.aula28_03.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person (
    @ColumnInfo(name = "first_name")
    var firstName : String,
    @ColumnInfo(name = "last_name")
    var lastName : String,
    var title : String
)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    override fun toString(): String {
        return "$title $firstName $lastName"
    }
}
