@package br.edu.ifpr.jonatas.aula28_03.db.dao

import androidx.room.*
import br.edu.ifpr.jonatas.aula28_03.entities.Person

@Dao
interface PersonDao {
    @Query("select * from people")
    fun getAll() : List<Person>
    
    @Query("select * from people where id = :id limit 1")
    fun findById(id : Int) : Person?
    
    @Query("select * from people where first_name like :firstName and last_name like :lastName")
    fun findByName(firstName : String, lastName : String) : List<Person> 
    
    @Insert
    fun insert(person : Person) : Long

    @Update
    fun update(person : Person)
    
    @Delete
    fun delete(person : Person)
}