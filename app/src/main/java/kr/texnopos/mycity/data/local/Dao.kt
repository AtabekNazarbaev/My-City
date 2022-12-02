package kr.texnopos.mycity.data.local

import androidx.room.Dao
import androidx.room.Query
import kr.texnopos.mycity.data.model.Detail
import kr.texnopos.mycity.data.model.ListById
import kr.texnopos.mycity.data.model.Main

@Dao
interface Dao {
    @Query("SELECT * FROM Category")
    fun getCategory(): List<Main>

    @Query("SELECT * FROM List WHERE categoryId =:id")
    fun getListById(id: Int): List<ListById>

    @Query("SELECT * FROM Detail WHERE list_id=:id")
    fun getInfo(id: Int):Detail
}
