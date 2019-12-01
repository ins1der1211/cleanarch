package ins1der.cleanarch.data.models

import com.squareup.moshi.JsonClass
import ins1der.cleanarch.domain.models.Employee

@JsonClass(generateAdapter = true)
data class EmployeesResponse(
    val employees: List<EmployeeAPI>
)

@JsonClass(generateAdapter = true)
data class EmployeeAPI(
    val id: String,
    val name: String,
    val salary: Int,
    val age: Int,
    val profileImage: String? = null
)

fun EmployeeAPI.mapToDomain(): Employee = Employee(
    id = id,
    age = age,
    name = name,
    profileImg = profileImage,
    salary = salary
)