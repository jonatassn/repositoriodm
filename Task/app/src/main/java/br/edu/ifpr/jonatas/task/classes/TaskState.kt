package br.edu.ifpr.jonatas.task.classes

import android.graphics.Color


enum class TaskState(val color : Int) {
    COMPLETED(Color.GREEN),
    INPROGRESS(Color.BLUE),
    OVERDUE(Color.RED)
}