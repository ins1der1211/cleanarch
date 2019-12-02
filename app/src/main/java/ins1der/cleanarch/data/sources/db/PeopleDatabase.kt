package ins1der.cleanarch.data.sources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ins1der.cleanarch.data.models.db.PersonEntity
import ins1der.cleanarch.data.sources.db.dao.PersonDao

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PeopleDatabase: RoomDatabase() {
    abstract fun peopleDao(): PersonDao
}