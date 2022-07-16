package uz.gita.newsappflowretrofitdagger.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newsappflowretrofitdagger.data.model.NewsData
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse

interface MainViewModel {

    val categoryLiveData: LiveData<List<NewsResponse.CategoryAdapterData>>
    val categoryTitleLiveData: LiveData<String>

    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>
    val messageLiveData: LiveData<Unit>
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val newsLiveData: LiveData<List<NewsEntity>>
    val emptyResultLiveData: LiveData<Unit>
    val openNextScreenLiveData: LiveData<NewsEntity>

    fun openDrawer()
    fun closeDrawer()
    fun load(category: String)
    fun openNextScreen(data: NewsEntity)
    fun onclickBurgerButton()


//    val openDrawerLiveData: LiveData<Unit>
//    val closeDrawerLiveData: LiveData<Unit>
//    val openDetailScreenLiveData: LiveData<NewsData>
//    val messageLiveData: LiveData<Unit>
//    val errorLiveData: LiveData<Unit>
//    val swipeLiveData: LiveData<Boolean>
//    val loadLiveData: LiveData<List<NewsData>>
//
//    fun onClickBurgerButton()
//    fun openDrawerLayout()
//    fun closeDrawerLayout()
//    fun onClickCategoryItem(list: List<NewsData>)
//    fun onClickForOpenDetailScreen(data: NewsData)
//    fun loadDataByCategory(category: String)


//    val categoriesLiveData: LiveData<List<NewsResponse.CategoryAdapterData>>
//    val categoryTitleLiveData: LiveData<String>
//
//    val openDrawerLayoutLiveData: LiveData<Unit>//
//    val closeDrawerLayoutLiveData: LiveData<Unit>//
//    val openDetailScreenLiveData: LiveData<NewsData>//
//    val notConnectionLiveData: LiveData<Unit>//
//    val errorLiveData: LiveData<String>//
//    val newsLiveData: LiveData<List<NewsData>>
//    val swipeLiveData: LiveData<Boolean>
//
//
//    fun openDrawableLayout()
//    fun closeDrawableLayout()
//    fun onClickBurgerButton()
//    fun onClickCategoryItem(categoryString: String)
//    fun openDetailScreen(data: NewsData)
//    fun loadDataByCategory(category: String)
}