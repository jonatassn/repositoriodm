package br.edu.ifpr.jonatas.todolist.datasource

import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single

interface TaskDataSource {
    fun getAll(): Single<List<Task>>
    fun insert(task: Task): Single<Long>
    fun insertAll(task: List<Task>): Completable
    fun update(task: Task): Completable
    fun removeAll(): Completable
    fun remove(task: Task): Completable
}