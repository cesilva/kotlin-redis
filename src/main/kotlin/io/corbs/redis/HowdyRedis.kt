package io.corbs.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @RequestMapping("ip")
    fun getIp(): Map<String, String> {
        val response = HashMap<String, String>()
        response.put("ip", AppUtils.getIp().toString())
        return response
    }

    @RequestMapping("datetime")
    fun getDateTime(): Map<String, String> {
        val response = HashMap<String, String>()
        response.put("datetime", AppUtils.now())
        return response
    }

    @RequestMapping("vcap/application")
    fun getVcapApplication(): Map<String, Any> {
        return CloudFoundryUtils.vcapApplication
    }

    @RequestMapping("vcap/services")
    fun getVcapServices(): Map<String, Any> {
        return CloudFoundryUtils.vcapServices
    }

}

