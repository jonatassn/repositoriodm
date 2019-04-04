package br.edu.ifpr.jonatas.aula28_03.ui

import br.edu.ifpr.jonatas.aula28_03.entities.Person

interface PeopleAdapterListener {
    fun personRemoved(person: Person)
    fun personClicked(person: Person)
}