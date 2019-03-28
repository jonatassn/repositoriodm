package br.edu.ifpr.jonatas.aula28_03.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifpr.jonatas.aula28_03.db.dao.PersonDao
import br.edu.ifpr.jonatas.aula28_03.entities.Person

@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao() : PersonDao
}