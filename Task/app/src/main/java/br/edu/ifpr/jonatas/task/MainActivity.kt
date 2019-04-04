package br.edu.ifpr.jonatas.task

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import br.edu.ifpr.jonatas.task.classes.TaskState
import br.edu.ifpr.jonatas.task.classes.ToDoTask
import br.edu.ifpr.jonatas.task.classes.dao.AppDatabase
import br.edu.ifpr.jonatas.task.classes.dao.ToDoTaskDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var taskDao : ToDoTaskDao
    lateinit var adapter: ArrayAdapter<ToDoTask>
    var taskEditing : ToDoTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task.db").allowMainThreadQueries().build()

        taskDao = db.TaskDao()

        btSave.setOnClickListener {
            saveTask()
            clear()
        }
        lvTask.setOnItemClickListener { _, _, position, _ ->
            btDelete.visibility = View.VISIBLE
            val task = adapter.getItem(position)
            editTask(task)
//            taskDao.delete(task)

        }
        btDelete.setOnClickListener {
            if(taskEditing != null) {
                val task = taskEditing
                task!!.title = txtTitle.text.toString()
                task!!.description = txtDescription.text.toString()
                task!!.state = cbState.isChecked
                taskDao.delete(task)
            }
            loadData()
        }

        loadData()
    }

    private fun editTask(task: ToDoTask) {
        txtTitle.setText(task.title)
        txtDescription.setText(task.description)
        cbState.isChecked = task.state
        Log.d("state",task.state.toString())

        txtTitle.requestFocus()

        taskEditing = task
    }

    private fun loadData() {
        val people = taskDao.getAll()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, people)
        lvTask.adapter = adapter
        clear()
    }

    private fun saveTask() {
        val taskTitle = txtTitle.text.toString()
        val taskDescription = txtDescription.text.toString()

        if(taskEditing != null) {
            taskEditing?.let { task ->
                task.title = taskTitle
                task.description = taskDescription
                task.state = cbState.isChecked
                taskDao.update(task)
            }
        }else {
            if(!txtTitle.text.toString().isEmpty() && !txtDescription.text.toString().isEmpty()) {
                val person = ToDoTask(taskTitle, taskDescription, cbState.isChecked)
                taskDao.insert(person)
            }
        }
        loadData()
//        txtTitle.clearFocus()
//        txtDescription.clearFocus()

    }

    private fun clear() {
        btDelete.visibility = View.INVISIBLE
        txtTitle.setText("")
        txtDescription.setText("")
        cbState.isChecked = false
        //txtTitle.requestFocus()

        taskEditing = null
    }
}
