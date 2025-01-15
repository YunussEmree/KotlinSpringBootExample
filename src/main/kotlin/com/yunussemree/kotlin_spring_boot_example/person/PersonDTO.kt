package com.yunussemree.kotlin_spring_boot_example.person

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class PersonDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var job: String? = null

    @NotNull
    @Size(max = 255)
    @PersonPhoneNumberUnique
    var phoneNumber: String? = null

}
