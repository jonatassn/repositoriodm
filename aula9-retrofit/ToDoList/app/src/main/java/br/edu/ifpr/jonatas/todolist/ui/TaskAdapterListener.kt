package br.edu.ifpr.jonatas.aula7_sqlite.ui

import br.edu.ifpr.jonatas.todolist.entities.Task

interface TaskAdapterListener {
    fun taskSaved(task: Task)
    fun taskRemoved(task: Task)
}