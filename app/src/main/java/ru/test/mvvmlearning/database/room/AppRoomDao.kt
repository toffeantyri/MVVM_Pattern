package ru.test.mvvmlearning.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.test.mvvmlearning.model.AppNote

@Dao
interface AppRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: AppNote)           //todo suspend

    @Delete
    fun delete(note: AppNote)           //todo suspend

}