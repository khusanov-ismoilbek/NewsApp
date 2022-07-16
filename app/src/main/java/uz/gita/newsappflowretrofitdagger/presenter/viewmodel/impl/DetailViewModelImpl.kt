package uz.gita.newsappflowretrofitdagger.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.DetailViewModel

//@HiltViewModel
class DetailViewModelImpl : ViewModel(), DetailViewModel {
    override val backButtonLiveData = MutableLiveData<Unit>()
    override val urlLiveData = MutableLiveData<String>()

    override fun onClickBackButton() {
        backButtonLiveData.value = Unit
    }

    override fun initUrl(url: String) {
        urlLiveData.value = url
    }
}