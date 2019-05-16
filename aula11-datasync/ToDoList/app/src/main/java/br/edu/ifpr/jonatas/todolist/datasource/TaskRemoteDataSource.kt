package br.edu.ifpr.jonatas.todolist.datasource

import br.edu.ifpr.jonatas.todolist.entities.Task
import br.edu.ifpr.jonatas.todolist.network.TasksServices
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TaskRemoteDataSource: TaskDataSource {
    var service: TasksServices

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create<TasksServices>(TasksServices::class.java)
    }

    override fun getAll(): Single<List<Task>> {
        return service.getAll()
    }

    override fun insert(task: Task) =
        service
            .createTask(task.title, task.description, task.done)
            .map { newTask -> newTask.remoteId!! }

    override fun update(task: Task) =
        service
            .update(task.remoteId!!, task.title, task.description, task.done)

    override fun remove(task: Task) =
        service
            .remove(task.remoteId!!)

    override fun insertAll(task: List<Task>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}