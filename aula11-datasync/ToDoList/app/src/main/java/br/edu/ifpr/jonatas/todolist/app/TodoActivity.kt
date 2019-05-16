package br.edu.ifpr.jonatas.todolist.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.jonatas.aula7_sqlite.ui.TaskAdapterListener
import br.edu.ifpr.jonatas.todolist.R
import br.edu.ifpr.jonatas.todolist.datasource.TaskRepository

import br.edu.ifpr.jonatas.todolist.entities.Task
import br.edu.ifpr.jonatas.todolist.ui.TaskAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.content_todo.*


class TodoActivity : AppCompatActivity(), TaskAdapterListener {

//    lateinit var taskDao: TaskDao
    lateinit var adapter: TaskAdapter
    lateinit var taskRepository: TaskRepository
    lateinit var disposables: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(toolbar)





        loadTasks()

        btAdd.setOnClickListener {
            val position = adapter.createTask()
            listTask.scrollToPosition(position)
        }
        swipeTasks.setOnRefreshListener {
            loadTasks()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun repositorySetup() {
        taskRepository = TaskRepository()
        disposables = CompositeDisposable()
    }

    private fun loadTasks() {
        swipeTasks.isRefreshing = true
        repositorySetup()
        taskRepository
            .getAll()
            .subscribe { tasks ->
                swipeTasks.isRefreshing = false
                if(tasks != null)
                    loadRecyclerView(tasks)
             }
            .addTo(disposables)

    }

    private fun loadRecyclerView(tasks : List<Task>) {

        adapter = TaskAdapter(tasks.toMutableList(), this)
        listTask.adapter = adapter
        listTask.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
    }

    override fun taskSaved(task: Task) {
        if (task.localId == 0L) {
            taskRepository
                .insert(task)
                .subscribe()
                .addTo(disposables)
        } else {
            taskRepository
                .update(task).subscribe()
        }
    }

    override fun taskRemoved(task: Task) { taskRepository.remove(task).subscribe()    }

}