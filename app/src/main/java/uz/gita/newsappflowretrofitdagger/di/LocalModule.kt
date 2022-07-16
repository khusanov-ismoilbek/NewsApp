package uz.gita.newsappflowretrofitdagger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappflowretrofitdagger.data.source.local.MyDatabase
import uz.gita.newsappflowretrofitdagger.data.source.local.dao.NewsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, "NewsDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @[Provides Singleton]
    fun getNewsDao(database: MyDatabase): NewsDao = database.getNewsDao()
}