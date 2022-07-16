package uz.gita.newsappflowretrofitdagger.data.source.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse

interface NewsApi {

    @GET("news")
    suspend fun getNewsByCategory(@Query("category") category: String): Response<NewsResponse.MainResponse> // Response bu Call clasiga o'xshash tayyor class, natijani qabul qilib olish uchun. Bu retrofitga tegishli
}