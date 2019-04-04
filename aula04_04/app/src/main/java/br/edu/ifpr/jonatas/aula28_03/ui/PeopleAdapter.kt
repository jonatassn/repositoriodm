package br.edu.ifpr.jonatas.aula28_03.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.jonatas.aula28_03.R
import br.edu.ifpr.jonatas.aula28_03.entities.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter(private var people: MutableList<Person>,
                    private var listener: PeopleAdapterListener) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    fun addPerson(person: Person) : Int {
        people.add(0, person)
        notifyItemInserted(0)
        return 0

    }

    fun updatePerson(person: Person) {
        val position = people.indexOf(person)
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        )

    override fun getItemCount() = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.fillUI(person)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(person: Person) {
            itemView.txtTitle.text = person.title
            itemView.txtFirstName.text = person.firstName
            itemView.txtLastName.text = person.lastName

            itemView.btRemove.setOnClickListener {
                with(this@PeopleAdapter) {
                    val position = people.indexOf(person)
                    people.removeAt(position)
                    notifyItemRemoved(position)
                    listener.personRemoved(person)
                }
            }
            itemView.setOnClickListener {
                with(this@PeopleAdapter) {
                    listener.personClicked(person)
                }
            }
        }
    }
}