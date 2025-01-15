package com.yunussemree.kotlin_spring_boot_example.person

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/people"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PersonController(
    private val personService: PersonService
) {

    @GetMapping
    fun getAllPeople(): ResponseEntity<List<PersonDTO>> = ResponseEntity.ok(personService.findAll())

    @GetMapping("/{id}")
    fun getPerson(@PathVariable(name = "id") id: Long): ResponseEntity<PersonDTO> =
            ResponseEntity.ok(personService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createPerson(@RequestBody @Valid personDTO: PersonDTO): ResponseEntity<Long> {
        val createdId = personService.create(personDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable(name = "id") id: Long, @RequestBody @Valid personDTO: PersonDTO):
            ResponseEntity<Long> {
        personService.update(id, personDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deletePerson(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        personService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
