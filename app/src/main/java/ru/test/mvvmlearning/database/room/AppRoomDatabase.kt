package ru.test.mvvmlearning.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.test.mvvmlearning.model.AppNote

@Database(entities = [AppNote::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getAppRoomDao(): AppRoomDao

    companion object {
        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, AppRoomDatabase::class.java, "database").build()
                return database as AppRoomDatabase
            } else return database as AppRoomDatabase
        }

    }

}