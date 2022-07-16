package uz.gita.newsappflowretrofitdagger.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappflowretrofitdagger.domain.repository.NewsRepository
import uz.gita.newsappflowretrofitdagger.domain.repository.impl.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}