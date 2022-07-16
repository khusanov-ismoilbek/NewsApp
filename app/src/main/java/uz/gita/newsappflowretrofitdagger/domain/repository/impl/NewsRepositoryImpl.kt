package uz.gita.newsappflowretrofitdagger.domain.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappflowretrofitdagger.data.source.local.dao.NewsDao
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.data.source.remote.api.NewsApi
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.toNewsEntity
import uz.gita.newsappflowretrofitdagger.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val database: NewsDao
) : NewsRepository {

    override fun getNewsFromNetwork(category: String): Flow<Result<List<NewsEntity>>> =
        flow<Result<List<NewsEntity>>> {
            val response = api.getNewsByCategory(category)
            if (response.isSuccessful) response.body()?.let {
                database.deleteNewsData(category)
                database.insertAllNews(it.articles.map {
                        data -> data.toNewsEntity(category)
                })
                emit(Result.success(database.getNewsByCategory(category)))
//                emit(Result.success(it.articles.map { data -> data.toNewsData() }))
            } else{
                emit(Result.failure(Exception(response.message())))
                Log.d("FFF", "${Exception(response.message())} + 1")
            }
        }.catch {
            Log.d("FFF", "${Exception(it).message} + 2")
            emit(Result.failure(Exception(it)))
        }.flowOn(Dispatchers.IO) // shu yerda bu metod qaysi oqimda ishlashini korsatiladi

    override fun getNewsFromRoom(category: String): Flow<Result<List<NewsEntity>>> =
        flow<Result<List<NewsEntity>>> {
            emit(Result.success(database.getNewsByCategory(category)))
//            emit(Result.success(database.getNewsByCategory(category).map { it.toNewsData() }))
        }.catch {
            emit(Result.failure(Exception(it)))
        }.flowOn(Dispatchers.IO)

    override fun getCategories(): Flow<Result<List<NewsResponse.CategoryAdapterData>>> =
        flow<Result<List<NewsResponse.CategoryAdapterData>>> {
            emit(
                Result.success(
                    listOf(
                        NewsResponse.CategoryAdapterData(id = 0, category = "all"),
                        NewsResponse.CategoryAdapterData(id = 1, category = "national"),
                        NewsResponse.CategoryAdapterData(id = 2, category = "business"),
                        NewsResponse.CategoryAdapterData(id = 3, category = "sports"),
                        NewsResponse.CategoryAdapterData(id = 4, category = "world"),
                        NewsResponse.CategoryAdapterData(id = 5, category = "politics"),
                        NewsResponse.CategoryAdapterData(id = 6, category = "technology"),
                        NewsResponse.CategoryAdapterData(id = 7, category = "startup"),
                        NewsResponse.CategoryAdapterData(id = 8, category = "entertainment"),
                        NewsResponse.CategoryAdapterData(id = 9, category = "science"),
                        NewsResponse.CategoryAdapterData(id = 10, category = "automobile")
                    )
                )
            )
        }.flowOn(Dispatchers.IO)


//    override fun getNewsByCategory(category: String): Flow<Result<List<NewsData>>> =
//        flow<Result<List<NewsData>>> {
//            try {
//                val response = api.getNewsByCategory(category)
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        emit(Result.success(it.articles.map { data -> data.toNewsData() }))
//                    }
//                } else {
//                    emit(Result.failure(Exception("Connection error")))
//                }
//            } catch (e: Exception) {
//                emit(Result.failure(e))
//            }
//        }.flowOn(Dispatchers.IO)
}