package br.edu.ifpr.jonatas.todolist.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.jonatas.aula7_sqlite.ui.TaskAdapterListener
import br.edu.ifpr.jonatas.todolist.R

import br.edu.ifpr.jonatas.todolist.entities.Task
import br.edu.ifpr.jonatas.todolist.ui.TaskAdapter

import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.content_todo.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import br.edu.ifpr.jonatas.todolist.network.TasksServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TodoActivity : AppCompatActivity(), TaskAdapterListener {

//    lateinit var taskDao: TaskDao
    lateinit var adapter: TaskAdapter
    lateinit var retrofit: Retrofit
    lateinit var service: TasksServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(toolbar)

//        val db =
//            Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java,
//                "todo.db"
//            )
//                .allowMainThreadQueries()
//                .build()
//        taskDao = db.taskDao()



        retrofitSetup()

        btAdd.setOnClickListener {
            val position = adapter.createTask()
            listTask.scrollToPosition(position)
        }
    }

    private fun retrofitSetup() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.20.23.189:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<TasksServices>(TasksServices::class.java)

        service.getAll().enqueue(object : Callback<List<Task>>{
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.e("ERRO", "ERRO ", t)
            }

            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                val tasks = response.body()
                if(tasks != null)
                    loadRecyclerView(tasks)


            }
        })
    }

    private fun loadRecyclerView(tasks : List<Task>) {
//        val tasks = taskDao.getAll()
        adapter = TaskAdapter(tasks.toMutableList(), this)
        listTask.adapter = adapter
        listTask.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
    }

    override fun taskSaved(task: Task) {
        if (task.id == 0L) {
//            task.id = taskDao.insert(task)
            val call = service.createTask(task.title,task.description,task.done)
            call.enqueue(object : Callback<Task>{
                override fun onFailure(call: Call<Task>, t: Throwable) {
                    Log.e("ERRO", "ERRO ", t)
                }

                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    val taskCreated = response.body()
                    if(taskCreated != null) {
                        task.id = taskCreated.id
                    }
                }
            })
        } else {
//            taskDao.update(task)
            val call = service.update(task.id, task.title, task.description, task.done)
            call.enqueue(object : Callback<Task>{
                override fun onFailure(call: Call<Task>, t: Throwable) {
                    Log.e("ERRO", "ERRO ", t)
                }

                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    Log.i("SUCCESS", "TASK UPDATED")
                }
            })
        }
    }

    override fun taskRemoved(task: Task) {
//        taskDao.remove(task)
        val call = service.remove(task.id)
        call.enqueue(object : Callback<Task>{
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Log.e("ERRO", "ERRO ", t)
            }

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                Log.i("SUCCESS", "TASK REMOVED")            }
        })
    }

}