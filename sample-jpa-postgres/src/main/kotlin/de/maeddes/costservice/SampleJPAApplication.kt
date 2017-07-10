package de.maeddes.costservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
@RestController
class SampleJPAApplication(val simpleRepository: SimpleRepository){

    @RequestMapping("/init")
    fun init() : String{
        simpleRepository.save(Simple("Matthias"))
        simpleRepository.save(Simple("Joschua"))
        return "initialized"
    }

    @RequestMapping("/clear")
    fun clear(): String {
        simpleRepository.deleteAll()
        return "cleared"
    }

    @RequestMapping("/list")
    fun list(): String{
        return simpleRepository.toString()
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(SampleJPAApplication::class.java, *args)
}

@Entity data class Simple(val content: String? = null) {

    @Id
    @GeneratedValue
    val id: Long? = null


}

@RepositoryRestResource(collectionResourceRel = "simple", path = "simple")
interface SimpleRepository : JpaRepository<Simple, Long>
