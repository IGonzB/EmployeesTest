package com.square.testapp.data.remote

import com.squareup.moshi.Json

data class EmployeeResponse(
    @field:Json(name = "employees")
    val employees : List<EmployeeDto>
)
