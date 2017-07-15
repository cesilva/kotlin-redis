package io.corbs.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*


fun main(args: Array<String>) {
    SpringApplication.run(HowdyRedis::class.java, *args)
}

@SpringBootApplication
@RestController
class HowdyRedis(@Autowired val personRepo: PersonRepo) {

    @PostMapping("/people")
    fun savePerson(@RequestBody person: Person) {
        personRepo.save(person)
    }

    @GetMapping("/people")
    fun getPeople(): List<Person> {
        return personRepo.findAll().toList()
    }

    @GetMapping("/people/{id}")
    fun getPerson(@PathVariable id: String): Person {
        return personRepo.findOne(id)
    }

    @GetMapping("/people/firstname/{firstName}")
    fun getByFirstName(@PathVariable firstName: String): List<Person> {
        return personRepo.findByFirstName(firstName)
    }

    @GetMapping("/people/lastname/{lastName}")
    fun getByLastName(@PathVariable lastName: String): List<Person> {
        return personRepo.findByLastName(lastName)
    }

}

