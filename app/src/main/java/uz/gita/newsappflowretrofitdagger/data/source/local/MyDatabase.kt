package uz.gita.newsappflowretrofitdagger.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import timber.log.Timber
import uz.gita.newsappflowretrofitdagger.data.source.local.dao.NewsDao
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

//    companion object {
//        private lateinit var instance: MyDatabase
//
//        fun getDatabase(context: Context): MyDatabase {
//            if (::instance.isInitialized) return instance
//            instance =
//                Room.databaseBuilder(context, MyDatabase::class.java, context.packageName)
//                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
//                    .build()
//            return instance
//        }
//    }
}