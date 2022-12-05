package kr.texnopos.mycity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.texnopos.mycity.data.model.Detail
import kr.texnopos.mycity.data.model.ListById
import kr.texnopos.mycity.data.model.Main

@Database(entities = [ListById::class, Main::class, Detail::class], version = 5, exportSchema = false)
abstract class ListDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: ListDatabase
        fun getInstance(context: Context): ListDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ListDatabase::class.java, "category_by_id")
                    .createFromAsset("MyCity.db")
                    .build()
            }
            return INSTANCE
        }
    }
    abstract fun listDao(): Dao
}
