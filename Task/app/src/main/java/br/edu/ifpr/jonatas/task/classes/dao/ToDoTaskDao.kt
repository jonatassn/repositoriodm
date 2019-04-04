package br.edu.ifpr.jonatas.task.classes.dao

import androidx.room.*
import br.edu.ifpr.jonatas.task.classes.ToDoTask

@Dao
interface ToDoTaskDao {
    @Query("select * from tasks")
    fun getAll() : List<ToDoTask>

    @Query("select * from tasks where id = :id limit 1")
    fun findById(id : Int) : ToDoTask?

    @Query("select * from tasks where title like :title")
    fun findByTitle(title : String) : List<ToDoTask>

    @Insert
    fun insert(task : ToDoTask)

    @Update
    fun update(task : ToDoTask)

    @Delete
    fun delete(task : ToDoTask)
}