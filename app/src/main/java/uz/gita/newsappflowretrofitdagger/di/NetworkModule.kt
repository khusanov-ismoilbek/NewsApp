package uz.gita.newsappflowretrofitdagger.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newsappflowretrofitdagger.data.source.remote.api.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // butun appda ishlaydi
class NetworkModule {

    @Provides
    @Singleton
    fun getOkHttpClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://inshorts.vercel.app/")
            .build()

    @Provides
    @Singleton
    fun getNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}

