package com.yunussemree.kotlin_spring_boot_example.person

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import org.springframework.web.servlet.HandlerMapping


/**
 * Validate that the phoneNumber value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [PersonPhoneNumberUniqueValidator::class])
annotation class PersonPhoneNumberUnique(
    val message: String = "{Exists.person.phoneNumber}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class PersonPhoneNumberUniqueValidator(
    private val personService: PersonService,
    private val request: HttpServletRequest
) : ConstraintValidator<PersonPhoneNumberUnique, String> {

    override fun isValid(`value`: String?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
                (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value.equals(personService.get(currentId.toLong()).phoneNumber,
                ignoreCase = true)) {
            // value hasn't changed
            return true
        }
        return !personService.phoneNumberExists(value)
    }

}
