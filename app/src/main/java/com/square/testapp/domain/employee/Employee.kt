package com.square.testapp.domain.employee


data class Employee(
    val uuid :String,
    val fullName : String,
    val phoneNumber : String,
    val emailAddress : String,
    val biography : String,
    val photoUrlSmall: String,
    val photoUrlBig : String,
    val team : String,
    val employeeType : EmployeeType
)
