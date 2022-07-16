package uz.gita.newsappflowretrofitdagger.presenter.viewmodel

import androidx.lifecycle.LiveData

interface DetailViewModel {
    val backButtonLiveData: LiveData<Unit>
    val urlLiveData: LiveData<String>


    fun onClickBackButton()
    fun initUrl(url: String)
}