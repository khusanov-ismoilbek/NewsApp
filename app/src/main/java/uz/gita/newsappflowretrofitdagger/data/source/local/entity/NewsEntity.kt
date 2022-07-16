package uz.gita.newsappflowretrofitdagger.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity // tablitsa xosil qilish
data class NewsEntity(
    val image: String,
    val readMore: String?,
    val author: String,
    val description: String,
    val inshortsLink: String,
    @PrimaryKey
    val title: String,
    val timestamp: String,
    val category: String
)