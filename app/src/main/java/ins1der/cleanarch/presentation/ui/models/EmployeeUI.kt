package ins1der.cleanarch.presentation.ui.models

import ins1der.cleanarch.domain.models.Employee

data class EmployeeUI(
    val id: String,
    val name: String,
    val salary: Int,
    val age: Int,
    val profileImg: String? = null
)

fun Employee.mapToUI(): EmployeeUI = EmployeeUI(
    id = id,
    salary = salary,
    profileImg = profileImg,
    name = name,
    age = age
)