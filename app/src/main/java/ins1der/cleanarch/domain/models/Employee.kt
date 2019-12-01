package ins1der.cleanarch.domain.models

data class Employee(
    val id: String,
    val name: String,
    val salary: Int,
    val age: Int,
    val profileImg: String? = null
)