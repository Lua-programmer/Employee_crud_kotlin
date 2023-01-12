package io.github.luaprogrammer.expat_dev.service

import io.github.luaprogrammer.expat_dev.exception.EmployeeNotFoundException
import io.github.luaprogrammer.expat_dev.helper.buildEmployee
import io.github.luaprogrammer.expat_dev.repository.EmployeeRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockKExtension::class)
internal class EmployeeServiceTest {

    @MockK
    private lateinit var employeeRepository: EmployeeRepository

    @InjectMockKs
    @SpyK
    private lateinit var employeeService: EmployeeService

    @Test
    fun `should return all customers`() {
        val fakeEmployees = listOf(buildEmployee(), buildEmployee())

        every { employeeRepository.findAll() } returns fakeEmployees
        val employees = employeeService.getAllEmployees()

        assertEquals(fakeEmployees, employees)
        verify(exactly = 1) { employeeRepository.findAll() }
        verify(exactly = 0) { employeeRepository.findByUserNameContaining(any()) }
    }

    @Test
    fun `should return employee by id`() {
        val id = Random().nextLong()
        val fakeEmployee = buildEmployee(id = id)

        every { employeeRepository.findById(id) } returns Optional.of(fakeEmployee)

        val employee = employeeService.getEmployeeById(id)

        assertEquals(fakeEmployee, employee)
        verify(exactly = 1) { employeeRepository.findById(id) }
    }

    @Test
    fun `should throw not found when find by id`() {
        val id = Random().nextLong()

        every { employeeRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<EmployeeNotFoundException> {
            employeeService.getEmployeeById(id)
        }

        assertEquals(HttpStatus.NOT_FOUND, error.statusCode)
        assertEquals("No matching employee was found", error.message)
        verify(exactly = 1) { employeeRepository.findById(id) }
    }

    @Test
    fun `should create employee`() {
        val fakeEmployee = buildEmployee()

        every { employeeRepository.save(fakeEmployee) } returns fakeEmployee

        employeeService.createEmployee(fakeEmployee)

        verify(exactly = 1) { employeeRepository.save(fakeEmployee) }
    }

    @Test
    fun `should update employee`() {
        val id = Random().nextLong()
        val fakeEmployee = buildEmployee(id = id)

        every { employeeRepository.existsById(id) } returns true
        every { employeeRepository.save(fakeEmployee) } returns fakeEmployee

        employeeService.updateEmployee(id, fakeEmployee)

        verify(exactly = 1) { employeeRepository.existsById(id) }
        verify(exactly = 1) { employeeRepository.save(fakeEmployee) }
    }

}