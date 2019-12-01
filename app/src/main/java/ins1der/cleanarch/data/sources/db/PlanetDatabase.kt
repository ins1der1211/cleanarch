package ins1der.cleanarch.data.sources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ins1der.cleanarch.data.models.db.PlanetEntity
import ins1der.cleanarch.data.sources.db.dao.PlanetDao

@Database(entities = arrayOf(PlanetEntity::class), version = 1)
abstract class PlanetDatabase: RoomDatabase() {
    abstract fun planetDao(): PlanetDao
}