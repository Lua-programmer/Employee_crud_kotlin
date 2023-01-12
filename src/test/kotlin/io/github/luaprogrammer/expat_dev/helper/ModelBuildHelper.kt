package io.github.luaprogrammer.expat_dev.helper

import io.github.luaprogrammer.expat_dev.model.Employee
import java.time.LocalDate
import java.util.UUID

fun buildEmployee(
    id: Long? = null,
    userName: String = "Employee name",
    firstName: String = "Employee name",
    middleName: String? = "Employee name",
    lastName: String = "Employee name",
    emailId: String = "${UUID.randomUUID()}@gmail.com",
    dayOfBirth: LocalDate = LocalDate.parse("2000-03-18")
) = Employee(
    id = id,
    userName = userName,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    emailId = emailId,
    dayOfBirth = dayOfBirth
)