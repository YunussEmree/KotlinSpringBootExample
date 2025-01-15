package com.yunussemree.kotlin_spring_boot_example.person

import org.springframework.data.jpa.repository.JpaRepository


interface PersonRepository : JpaRepository<Person, Long> {

    fun existsByPhoneNumberIgnoreCase(phoneNumber: String?): Boolean

}
