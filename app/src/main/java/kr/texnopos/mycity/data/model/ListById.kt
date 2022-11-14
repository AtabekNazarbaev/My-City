package kr.texnopos.mycity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "List")
data class ListById(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String,
    val categoryId: Int
)
