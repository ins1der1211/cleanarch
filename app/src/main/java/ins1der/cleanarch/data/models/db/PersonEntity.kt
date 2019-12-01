package ins1der.cleanarch.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ins1der.cleanarch.domain.models.Person

@Entity(tableName = "people")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String
)

fun PersonEntity.mapToDomain(): Person = Person(
    name = name,
    url = url
)