package com.square.testapp.data.mappers

import com.square.testapp.data.remote.EmployeeDto
import com.square.testapp.data.remote.EmployeeResponse
import com.square.testapp.domain.employee.Employee
import com.square.testapp.domain.employee.EmployeeType
import com.square.testapp.domain.employee.EmployeesData


fun EmployeeResponse.toEmployeesInfo(): EmployeesData {

    val employees = employees.map {
        Employee(
            uuid = it.uuid,
            fullName = it.fullName,
            phoneNumber = it.phoneNumber,
            emailAddress = it.emailAddress,
            biography = it.biography,
            photoUrlSmall = it.photoUrlSmall,
            photoUrlBig = it.photoUrlBig,
            team = it.team,
            employeeType = EmployeeType.valueOf(it.employeeType)
        )
    }.toList()

    return EmployeesData(employees)
}
