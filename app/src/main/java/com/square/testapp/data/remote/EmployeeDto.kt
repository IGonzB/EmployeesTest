package com.square.testapp.data.remote

import com.squareup.moshi.Json

data class EmployeeDto(
 @field:Json(name = "uuid")
 val uuid :String,
 @field:Json(name = "full_name")
 val fullName : String,
 @field:Json(name = "phone_number")
 val phoneNumber : String,
 @field:Json(name = "email_address")
 val emailAddress : String,
 @field:Json(name = "biography")
 val biography : String,
 @field:Json(name = "photo_url_small")
 val photoUrlSmall: String,
 @field:Json(name = "photo_url_large")
 val photoUrlBig : String,
 @field:Json(name = "team")
 val team : String,
 @field:Json(name = "employee_type")
 val employeeType : String
)