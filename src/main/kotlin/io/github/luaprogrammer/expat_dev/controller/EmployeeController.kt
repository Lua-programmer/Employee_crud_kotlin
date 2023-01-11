package io.github.luaprogrammer.expat_dev.controller

import io.github.luaprogrammer.expat_dev.model.Employee
import io.github.luaprogrammer.expat_dev.service.EmployeeService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/employees")
class EmployeeController(private val service: EmployeeService) {

    @GetMapping
    fun getAllEmployees(): List<Employee> = service.getAllEmployees()

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable("id") employeeId: Long): Employee = service.getEmployeeById(employeeId)

    @PostMapping()
    fun createEmployee(@RequestBody payload: Employee): Employee = service.createEmployee(payload)

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable("id") employeeId: Long, @RequestBody payload: Employee): Employee =
        service.updateEmployee(employeeId, payload)

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable("id") employeeId: Long): Unit = service.deleteEmployeeById(employeeId)
}