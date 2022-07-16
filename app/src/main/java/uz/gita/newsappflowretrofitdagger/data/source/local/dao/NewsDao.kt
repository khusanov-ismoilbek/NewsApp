package uz.gita.newsappflowretrofitdagger.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.newsappflowretrofitdagger.data.model.NewsData
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("Select * from NewsEntity Where NewsEntity.category = :category")
    suspend fun getNewsByCategory(category: String): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict = OnConflictStrategy.REPLACE
    suspend fun insertAllNews(list: List<NewsEntity>)

    @Query("DELETE from NewsEntity Where category = :category")
    suspend fun deleteNewsData(category: String)
}