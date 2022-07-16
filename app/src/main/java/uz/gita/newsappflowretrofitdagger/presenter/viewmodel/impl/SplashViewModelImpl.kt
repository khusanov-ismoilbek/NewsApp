package uz.gita.newsappflowretrofitdagger.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.newsappflowretrofitdagger.domain.repository.NewsRepository
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(), SplashViewModel {

    override val openNextScreenLiveData = MutableLiveData<Unit>()
//    override val countLiveData = MutableLiveData<Int>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            openNextScreenLiveData.postValue(Unit)
        }
//        repository.myCounter().onEach {
//            it.onSuccess { amount ->
//                countLiveData.value = amount
//            }
//            it.onFailure {
//
//            }
//        }   .launchIn(viewModelScope)
    }
}