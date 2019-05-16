package br.edu.ifpr.jonatas.todolist.datasource

import androidx.room.Room
import br.edu.ifpr.jonatas.todolist.app.TodoApplication
import br.edu.ifpr.jonatas.todolist.db.AppDatabase
import br.edu.ifpr.jonatas.todolist.db.dao.TaskDao
import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TaskLocalDataSource : TaskDataSource {
    var taskDao: TaskDao

    init {
                val db =
                    Room.databaseBuilder(
                        TodoApplication.appContext,
                        AppDatabase::class.java,
                        "todo.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                taskDao = db.taskDao()
    }

    override fun getAll() = taskDao.getAll()

    override fun insert(task: Task) = taskDao.insert(task)

    override fun update(task: Task) = taskDao.update(task)

    override fun remove(task: Task) = taskDao.remove(task)

    override fun insertAll(task: List<Task>) = taskDao.insertAll(task)

    override fun removeAll() = taskDao.removeAll()
}