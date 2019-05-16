package br.edu.ifpr.jonatas.todolist.datasource

import br.edu.ifpr.jonatas.todolist.app.TodoApplication
import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
class TaskRepository: TaskDataSource {
    val dataSource : TaskDataSource
        get() = if(TodoApplication.app.networkAvailable) TaskRemoteDataSource() else TaskLocalDataSource()

    val remoteDataSource = TaskRemoteDataSource()
    val taskLocalDataSource = TaskLocalDataSource()

    override fun getAll() =
        if(TodoApplication.app.networkAvailable) {
            remoteDataSource
                .getAll()
                .doOnSuccess { remoteTasks ->
                    taskLocalDataSource
                        .removeAll()
                        .doOnComplete {
                            taskLocalDataSource
                                .insertAll(remoteTasks)
                                .subscribe()
                        }
                        .subscribe()
                }

        } else {
            taskLocalDataSource.getAll()
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun insert(task: Task): Single<Long> {
        task.localStatus = Task.STATUS_INSERTED
        return taskLocalDataSource
            .insert(task)
            .doOnSuccess { localId ->
                task.localId = localId
                if(TodoApplication.app.networkAvailable)
                    remoteDataSource
                        .insert(task)
                        .doOnSuccess { remoteId ->
                            task.remoteId = remoteId
                            task.localStatus = null
                            taskLocalDataSource
                                .taskDao.update(task)
                                .subscribe()
                        }
                        .subscribe()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun update(task: Task) =
        remoteDataSource
            .update(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun remove(task: Task) =
        remoteDataSource
            .remove(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun insertAll(task: List<Task>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

