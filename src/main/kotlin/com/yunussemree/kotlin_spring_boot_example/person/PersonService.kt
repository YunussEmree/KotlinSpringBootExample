package com.yunussemree.kotlin_spring_boot_example.person

import com.yunussemree.kotlin_spring_boot_example.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun findAll(): List<PersonDTO> {
        val persons = personRepository.findAll(Sort.by("id"))
        return persons.stream()
                .map { person -> mapToDTO(person, PersonDTO()) }
                .toList()
    }

    fun `get`(id: Long): PersonDTO = personRepository.findById(id)
            .map { person -> mapToDTO(person, PersonDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(personDTO: PersonDTO): Long {
        val person = Person()
        mapToEntity(personDTO, person)
        return personRepository.save(person).id!!
    }

    fun update(id: Long, personDTO: PersonDTO) {
        val person = personRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(personDTO, person)
        personRepository.save(person)
    }

    fun delete(id: Long) {
        personRepository.deleteById(id)
    }

    private fun mapToDTO(person: Person, personDTO: PersonDTO): PersonDTO {
        personDTO.id = person.id
        personDTO.name = person.name
        personDTO.job = person.job
        personDTO.phoneNumber = person.phoneNumber
        return personDTO
    }

    private fun mapToEntity(personDTO: PersonDTO, person: Person): Person {
        person.name = personDTO.name
        person.job = personDTO.job
        person.phoneNumber = personDTO.phoneNumber
        return person
    }

    fun phoneNumberExists(phoneNumber: String?): Boolean =
            personRepository.existsByPhoneNumberIgnoreCase(phoneNumber)

}
