package ins1der.cleanarch.data.sources.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ins1der.cleanarch.data.models.db.PersonEntity

@Dao
interface PersonDao {

    @Query("SELECT * FROM people ORDER BY url ASC")
    fun getPeople(): DataSource<Int, PersonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPeople(people: List<PersonEntity>)
}