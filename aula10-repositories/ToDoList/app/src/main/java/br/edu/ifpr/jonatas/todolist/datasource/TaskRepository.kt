package br.edu.ifpr.jonatas.todolist.datasource

import br.edu.ifpr.jonatas.todolist.app.TodoApplication
import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TaskRepository: TaskDataSource {
    val dataSource : TaskDataSource
        get() = if(TodoApplication.app.networkAvailable) TaskRemoteDataSource() else TaskLocalDataSource()

    override fun getAll() = dataSource.getAll().subscribeOn(Schedulers.io())

    override fun insert(task: Task) = dataSource.insert(task).subscribeOn(Schedulers.io())

    override fun update(task: Task) = dataSource.update(task).subscribeOn(Schedulers.io())

    override fun remove(task: Task) = dataSource.remove(task).subscribeOn(Schedulers.io())

}