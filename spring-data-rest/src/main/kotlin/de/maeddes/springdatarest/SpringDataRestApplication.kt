package de.maeddes.springdatarest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
@RestController
class SpringDataRestApplication (val customerRepository: CustomerRepository){

    @RequestMapping("/hello")
    fun hello(): String{
        return "Hello from Kotlin"
    }

    @RequestMapping("/init")
    fun init(): String{
        customerRepository.save(Greeting("German","Hallo"))
        customerRepository.save(Greeting("English","Hello"))
        customerRepository.save(Greeting("Spanish","Hola"))
        customerRepository.save(Greeting("Schwaebisch","Hallole"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringDataRestApplication::class.java, *args)
}

@Entity
class Greeting(
        language: String,
        greeting: String,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0)

@RepositoryRestResource(collectionResourceRel = "greetings", path = "greetings")
interface CustomerRepository : CrudRepository<Greeting, Long> {
    fun findByLanguage(language: String): List<Greeting>
}
