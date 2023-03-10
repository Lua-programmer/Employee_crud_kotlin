package io.github.luaprogrammer.expat_dev.repository

import io.github.luaprogrammer.expat_dev.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {
    fun findByUserNameContaining(userName: String)
}