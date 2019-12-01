package ins1der.cleanarch.data.sources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ins1der.cleanarch.data.sources.db.dao.PersonDao

@Database(entities = arrayOf(PersonDao::class), version = 1)
abstract class PeopleDatabase: RoomDatabase() {
    abstract fun peopleDao(): PersonDao
}