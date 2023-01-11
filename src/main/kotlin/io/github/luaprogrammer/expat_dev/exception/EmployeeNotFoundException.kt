package io.github.luaprogrammer.expat_dev.exception

import org.springframework.http.HttpStatus

class EmployeeNotFoundException(val statusCode: HttpStatus, override val message: String) : Exception() {
}