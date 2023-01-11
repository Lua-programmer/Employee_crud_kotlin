package io.github.luaprogrammer.expat_dev.service

import io.github.luaprogrammer.expat_dev.exception.EmployeeNotFoundException
import io.github.luaprogrammer.expat_dev.model.Employee
import io.github.luaprogrammer.expat_dev.repository.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val repository: EmployeeRepository) {

    fun getAllEmployees(): List<Employee> = repository.findAll()

    fun getEmployeeById(employeeId: Long): Employee = repository.findById(employeeId)
        .orElseThrow { EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found") }

    fun createEmployee(employee: Employee): Employee = repository.save(employee)

    fun updateEmployee(employeeId: Long, employee: Employee): Employee {
        return if (repository.existsById(employeeId)) {
            repository.save(
                Employee(
                    id = employeeId,
                    userName = employee.userName,
                    firstName = employee.firstName,
                    middleName = employee.middleName,
                    lastName = employee.lastName,
                    emailId = employee.emailId,
                    dayOfBirth = employee.dayOfBirth
                )
            )
        } else throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }

    fun deleteEmployeeById(employeeId: Long) {
        return if (repository.existsById(employeeId)) {
            repository.deleteById(employeeId)
        } else throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }
}