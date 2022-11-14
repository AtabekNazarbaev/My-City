package kr.texnopos.mycity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.texnopos.mycity.data.model.ListById
import kr.texnopos.mycity.data.model.Main

@Database(entities = [Main::class, ListById::class], version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: MyDatabase
        fun getInstance(context: Context): MyDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "category")
                    .createFromAsset("MyCity.db")
                    .build()
            }
            return INSTANCE
        }
    }
    abstract fun categoryDao(): Dao
}
