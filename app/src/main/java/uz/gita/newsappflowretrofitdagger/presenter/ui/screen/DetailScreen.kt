package uz.gita.newsappflowretrofitdagger.presenter.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappflowretrofitdagger.R
import uz.gita.newsappflowretrofitdagger.databinding.ScreenDetailsBinding
import uz.gita.newsappflowretrofitdagger.databinding.ScreenMainNavBinding
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.DetailViewModel
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.impl.DetailViewModelImpl

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_details) {
    private val binding by viewBinding(ScreenDetailsBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val args: DetailScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.backButtonLiveData.observe(viewLifecycleOwner, backButtonObserver)
        viewModel.urlLiveData.observe(viewLifecycleOwner, urlObserver)

        args.siteUrl?.let {
            viewModel.initUrl(it)
        }

        binding.backButton.setOnClickListener{
            viewModel.onClickBackButton()
        }
        binding.detailScreenTitle.text = args.title
    }

    private val backButtonObserver = Observer<Unit>{
        findNavController().popBackStack()
    }

    private val urlObserver = Observer<String>{
        binding.webView.loadUrl(it)
    }

}