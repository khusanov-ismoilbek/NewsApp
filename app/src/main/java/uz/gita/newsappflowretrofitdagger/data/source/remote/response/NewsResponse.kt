package uz.gita.newsappflowretrofitdagger.data.source.remote.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity

sealed class NewsResponse {

    data class MainResponse(
        @field:SerializedName("total")
        val total: Int,

        @PrimaryKey
        @field:SerializedName("category")
        val category: String,

        @field:SerializedName("articles")
        val articles: List<ArticlesData>
    )

    data class ArticlesData(
        @field:SerializedName("image")
        val image: String,

        @field:SerializedName("read_more")
        val readMore: String,

        @field:SerializedName("author")
        val author: String,

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("inshorts_link")
        val inshortsLink: String,

        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("timestamp")
        val timestamp: String
    )

    data class CategoryAdapterData(
        val id: Int,
        val category: String
    )
}

fun NewsResponse.ArticlesData.toNewsEntity(category: String): NewsEntity = NewsEntity(
    image, readMore, author, description, inshortsLink, title, timestamp, category
)