package kr.texnopos.mycity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Detail")
data class Detail(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var address: String = "",
    var phone: String = "",
    var time: String = "",
    var list_id: Int = 0,
    var category_id: Int = 0
)
