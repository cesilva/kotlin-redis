package io.corbs.redis

import org.springframework.data.repository.CrudRepository

interface PersonRepo : CrudRepository<Person, String> {
    fun findByFirstName(firstName: String): List<Person>
    fun findByLastName(lastName: String): List<Person>
}