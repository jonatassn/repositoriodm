package br.edu.ifpr.jonatas.aula28_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.edu.ifpr.jonatas.aula28_03.db.AppDatabase
import br.edu.ifpr.jonatas.aula28_03.db.dao.PersonDao
import br.edu.ifpr.jonatas.aula28_03.entities.Person
import br.edu.ifpr.jonatas.aula28_03.ui.PeopleAdapter
import br.edu.ifpr.jonatas.aula28_03.ui.PeopleAdapterListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PeopleAdapterListener {
    lateinit var personDao : PersonDao
    lateinit var adapter: PeopleAdapter
    var personEditing : Person? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "person.db").allowMainThreadQueries().build()

        personDao = db.personDao()

        btSave.setOnClickListener {
            savePerson()
        }
        loadData()

//        listPeople.setOnItemClickListener { _, _, position, _ ->
//            val person = adapter.getItem(position) as Person
//            editPerson(person)
//        }
//
//        listPeople.setOnItemLongClickListener { _, _, position, _ ->
//            val person = adapter.getItem(position) as Person
//            detelePerson(person)
//
//        }
    }

    private fun detelePerson(person: Person): Boolean {
        personDao.delete(person)
        loadData()
        return true
    }

    private fun editPerson(person: Person) {
        txtFirstName.setText(person.firstName)
        txtLastName.setText(person.lastName)
        txtTitle.setText(person.title)

        txtFirstName.requestFocus()

        personEditing = person
    }

    private fun savePerson() {
        val firstName = txtFirstName.text.toString()
        val lastName = txtLastName.text.toString()
        val title = txtTitle.text.toString()

        Log.d("person", "firstname: ${firstName} lastname: ${lastName} title: ${title}")

        if(personEditing != null) {
            personEditing?.let { person ->
                person.firstName = firstName
                person.lastName = lastName
                person.title = title
                personDao.update(person)

                adapter.updatePerson(person)
            }
        }else {
            var person = Person(firstName,lastName,title)
            val id = personDao.insert(person).toInt()
            person = personDao.findById(id)!!

            val position = adapter.addPerson(person)
            rvPeople.scrollToPosition(0)
        }
//        loadData()
    }

    private fun loadData() {
        val people = personDao.getAll()

        adapter = PeopleAdapter(people.toMutableList(), this)
        rvPeople.adapter = adapter
        rvPeople.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL, false)

        clear()
    }

    private fun clear() {
        txtFirstName.setText("")
        txtLastName.setText("")
        txtTitle.setText("")

        txtFirstName.requestFocus()

        personEditing = null
    }

    override fun personRemoved(person: Person) {
        personDao.delete(person)
    }

    override fun personClicked(person: Person) {
        editPerson(person)
    }
}
