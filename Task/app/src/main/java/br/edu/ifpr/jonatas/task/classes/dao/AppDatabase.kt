package br.edu.ifpr.jonatas.task.classes.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifpr.jonatas.task.classes.ToDoTask

@Database(entities = arrayOf(ToDoTask::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TaskDao() : ToDoTaskDao
}