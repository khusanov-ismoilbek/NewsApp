package uz.gita.newsappflowretrofitdagger.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappflowretrofitdagger.data.model.NewsData
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse

interface NewsRepository {
    fun getNewsFromNetwork(category: String): Flow<Result<List<NewsEntity>>>
    fun getNewsFromRoom(category: String): Flow<Result<List<NewsEntity>>>
    fun getCategories(): Flow<Result<List<NewsResponse.CategoryAdapterData>>>
}