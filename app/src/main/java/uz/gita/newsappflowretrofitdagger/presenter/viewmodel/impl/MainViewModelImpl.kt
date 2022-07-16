package uz.gita.newsappflowretrofitdagger.presenter.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappflowretrofitdagger.data.model.NewsData
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse
import uz.gita.newsappflowretrofitdagger.domain.repository.NewsRepository
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.MainViewModel
import uz.gita.newsappflowretrofitdagger.utill.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) :
    ViewModel(), MainViewModel {


    //    override val categoriesLiveData = MutableLiveData<List<NewsResponse.CategoryAdapterData>>()
//    override val categoryTitleLiveData = MutableLiveData<String>()

//    override val openDrawerLayoutLiveData = MutableLiveData<Unit>()
//    override val closeDrawerLayoutLiveData = MutableLiveData<Unit>()
//    override val openDetailScreenLiveData = MutableLiveData<NewsData>()
//    override val notConnectionLiveData = MutableLiveData<Unit>()
//    override val errorLiveData = MutableLiveData<String>()
//    override val newsLiveData = MutableLiveData<List<NewsData>>()
//    override val swipeLiveData = MutableLiveData<Boolean>()
//
//    init {
//        categoriesLiveData.value = listOf(
//            NewsResponse.CategoryAdapterData(id = 0, category = "all"),
//            NewsResponse.CategoryAdapterData(id = 1, category = "national"),
//            NewsResponse.CategoryAdapterData(id = 2, category = "business"),
//            NewsResponse.CategoryAdapterData(id = 3, category = "sports"),
//            NewsResponse.CategoryAdapterData(id = 4, category = "world"),
//            NewsResponse.CategoryAdapterData(id = 5, category = "politics"),
//            NewsResponse.CategoryAdapterData(id = 6, category = "technology"),
//            NewsResponse.CategoryAdapterData(id = 7, category = "startup"),
//            NewsResponse.CategoryAdapterData(id = 8, category = "entertainment"),
//            NewsResponse.CategoryAdapterData(id = 9, category = "science"),
//            NewsResponse.CategoryAdapterData(id = 10, category = "automobile"),
//        )
//        categoryTitleLiveData.value = "all"
//
//        loadDataByCategory("all")
//    }
//
//    override fun openDrawableLayout() {
//        openDrawerLayoutLiveData.value = Unit
//    }
//
//    override fun closeDrawableLayout() {
//        closeDrawerLayoutLiveData.value = Unit
//    }
//
//    override fun onClickBurgerButton() {
//        openDrawerLayoutLiveData.value = Unit
//    }
//
//    override fun openDetailScreen(data: NewsData) {
//        openDetailScreenLiveData.value = data
//    }
//
//    override fun loadDataByCategory(category: String) {
//        if (!isConnected()) {
//            notConnectionLiveData.value = Unit
//            swipeLiveData.value = true
//            return
//        }
//        swipeLiveData.value = true
//        closeDrawerLayoutLiveData.value = Unit
//        repository.getNewsByCategory(category).onEach {
//            swipeLiveData.value = false
//
//            it.onSuccess {
//                Log.d("EEE", "$it")
//                newsLiveData.value = it
////                categoryTitleLiveData.value =
//            }
//
//            it.onFailure {
//                Log.d("FFF", "$it")
//                errorLiveData.value = it.message
//            }
//        }.launchIn(viewModelScope)
//    }
    override val categoryLiveData = MutableLiveData<List<NewsResponse.CategoryAdapterData>>()//
    override val categoryTitleLiveData = MutableLiveData<String>()//
    override val openDrawerLiveData = MutableLiveData<Unit>()//
    override val closeDrawerLiveData = MutableLiveData<Unit>()//
    override val messageLiveData = MutableLiveData<Unit>()//
    override val errorLiveData = MutableLiveData<String>()//
    override val progressLiveData = MutableLiveData<Boolean>()//
    override val newsLiveData = MutableLiveData<List<NewsEntity>>()//
    override val emptyResultLiveData = MutableLiveData<Unit>()//
    override val openNextScreenLiveData = MutableLiveData<NewsEntity>()

    init {
        progressLiveData.value = true
        repository.getCategories().onEach {
            it.onSuccess { list ->
                progressLiveData.value = false
                categoryLiveData.value = list
                categoryTitleLiveData.value = list[0].category
                load("all")
            }
        }.launchIn(viewModelScope)
    }

    override fun openDrawer() {
        openDrawerLiveData.value = Unit
    }

    override fun closeDrawer() {
        closeDrawerLiveData.value = Unit
    }

    override fun onclickBurgerButton() {
        openDrawerLiveData.value = Unit
    }

    override fun openNextScreen(data: NewsEntity) {
        openNextScreenLiveData.value = data
        closeDrawerLiveData.value = Unit
    }

    override fun load(category: String) {
        progressLiveData.value = true
        if (isConnected()) loadNetwork(category)
        else loadLocale(category)
    }

    private fun loadNetwork(category: String) {
        categoryTitleLiveData.value = category
        repository.getNewsFromNetwork(category).onEach {
            it.onSuccess { list ->
                progressLiveData.value = false
                if (list.isEmpty()) emptyResultLiveData.value = Unit
                else newsLiveData.value = list
            }
            it.onFailure { throwable ->
                progressLiveData.value = false
                Log.d("FFF", "${throwable.message} + 4")
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }
    private fun loadLocale(category: String) {
        progressLiveData.value = false
        categoryTitleLiveData.value = category
        messageLiveData.value = Unit
        repository.getNewsFromRoom(category).onEach {
            it.onSuccess { list ->
                if (list.isEmpty()) emptyResultLiveData.value = Unit
                else newsLiveData.value = list
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }
}
