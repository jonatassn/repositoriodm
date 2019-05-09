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
            .baseUrl("http://10.20.23.189:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create<TasksServices>(TasksServices::class.java)
    }

    override fun getAll(): Single<List<Task>> {
        return service.getAll().subscribeOn(Schedulers.io())
    }

    override fun insert(task: Task): Single<Long> {
        return service.createTask(task.title, task.description, task.done)
            .map { task.id }
            .subscribeOn(Schedulers.io())
    }

    override fun update(task: Task): Completable {
        return service.update(task.id, task.title, task.description, task.done).subscribeOn(Schedulers.io())
    }

    override fun remove(task: Task): Completable {
        return service.remove(task.id).subscribeOn(Schedulers.io())
    }
}