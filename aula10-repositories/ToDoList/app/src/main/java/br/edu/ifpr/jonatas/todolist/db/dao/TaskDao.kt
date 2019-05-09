package br.edu.ifpr.jonatas.todolist.db.dao

import androidx.room.*
import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): Single<List<Task>>

//    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
//    fun findById(id: Int): Task?

    @Insert
    fun insert(task: Task): Single<Long>

    @Update
    fun update(task: Task): Completable

    @Delete
    fun remove(task: Task): Completable
}