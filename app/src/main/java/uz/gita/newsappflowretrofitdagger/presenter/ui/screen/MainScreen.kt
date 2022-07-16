package uz.gita.newsappflowretrofitdagger.presenter.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappflowretrofitdagger.R
import uz.gita.newsappflowretrofitdagger.data.model.NewsData
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse
import uz.gita.newsappflowretrofitdagger.databinding.ScreenMainNavBinding
import uz.gita.newsappflowretrofitdagger.presenter.ui.adapter.CategoryAdapter
import uz.gita.newsappflowretrofitdagger.presenter.ui.adapter.NewsAdapter
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.MainViewModel
import uz.gita.newsappflowretrofitdagger.presenter.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main_nav) {
    private val binding by viewBinding(ScreenMainNavBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val categoryAdapter = CategoryAdapter()
    private val adapter = NewsAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openDrawerLiveData.observe(viewLifecycleOwner, openDrawerObserver)
        viewModel.closeDrawerLiveData.observe(viewLifecycleOwner, closeDrawerObserver)
        viewModel.messageLiveData.observe(viewLifecycleOwner, messageObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.categoryTitleLiveData.observe(viewLifecycleOwner, categoryTitleObserver)
        viewModel.emptyResultLiveData.observe(viewLifecycleOwner, emptyResultObserver)
        viewModel.categoryLiveData.observe(viewLifecycleOwner, categoryObserver)
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.openNextScreenLiveData.observe(this@MainScreen, openNextScreenObserver)

        categoryAdapter.setOnCategoryClickListener {
            viewModel.load(it)
            viewModel.closeDrawer()
        }
        binding.burgerButton.setOnClickListener{
            viewModel.openDrawer()
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.load(binding.categoryTitle.text.toString())
        }

        adapter.setOnClickItemListener {
            viewModel.openNextScreen(it)
        }
    }

    private val openDrawerObserver = Observer<Unit>{
        binding.drawer.openDrawer(GravityCompat.START)
    }
    private val closeDrawerObserver = Observer<Unit>{
        binding.drawer.closeDrawer(GravityCompat.START)
    }
    private val messageObserver = Observer<Unit> {
        Toast.makeText(requireContext(), R.string.message, Toast.LENGTH_SHORT).show()
    }
    private val errorObserver = Observer<String>{
        Log.d("TTT", "$it")
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val categoryTitleObserver = Observer<String> {
        binding.categoryTitle.text = it
    }
    private val categoryObserver = Observer<List<NewsResponse.CategoryAdapterData>> {
        categoryAdapter.submitList(it)
        binding.navigationLayout.listCategory.adapter = categoryAdapter
        binding.navigationLayout.listCategory.layoutManager = LinearLayoutManager(requireContext())
    }
    private val emptyResultObserver = Observer<Unit> {
        binding.noData.visibility = View.VISIBLE
        binding.listRV.visibility = View.GONE
    }
    private val newsObserver = Observer<List<NewsEntity>> {
        binding.noData.visibility = View.GONE
        binding.listRV.visibility = View.VISIBLE
        adapter.submitList(it)
        binding.listRV.adapter = adapter
        binding.listRV.layoutManager = LinearLayoutManager(requireContext())
    }
    private val progressObserver = Observer<Boolean> {
        binding.swipeRefresh.isRefreshing = it
    }
    private val openNextScreenObserver = Observer<NewsEntity> {
        findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(
            it.title,
            it.readMore
        ))
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
//        viewModel.openDrawerLayoutLiveData.observe(viewLifecycleOwner, openDrawerLayoutObserver)
//        viewModel.closeDrawerLayoutLiveData.observe(viewLifecycleOwner, closeDrawerLayoutObserver)
//        viewModel.openDetailScreenLiveData.observe(viewLifecycleOwner, openDetailScreenObserver)
//        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
//        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)
//        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
//        viewModel.swipeLiveData.observe(viewLifecycleOwner, swipeObserver)
//        viewModel.categoriesLiveData.observe(viewLifecycleOwner, categoriesObserver)
//        viewModel.categoryTitleLiveData.observe(viewLifecycleOwner, categoryTitleObserver)
//
//        burgerButton.setOnClickListener { viewModel.openDrawableLayout() }
//        swipeRefresh.setOnRefreshListener {
//            Log.d("TTT", "${viewModel.categoryTitleLiveData.value.toString()}")
//            viewModel.loadDataByCategory(viewModel.categoryTitleLiveData.value.toString())
//        }
//        categoryAdapter.setOnCategoryClickListener {
//            Log.d("PPP", "category = $it")
//            viewModel.loadDataByCategory(it)
//            viewModel.closeDrawableLayout()
//        }
//        adapter.setOnClickItemListener {
//            viewModel.openDetailScreen(it)
//        }
//    }
//
//    private val openDrawerLayoutObserver = Observer<Unit> {
//        binding.drawer.openDrawer(GravityCompat.START)
//    }
//    private val closeDrawerLayoutObserver = Observer<Unit> {
//        binding.drawer.closeDrawer(GravityCompat.START)
//    }
//    private val errorObserver = Observer<String> {
////        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//    }
//    private val notConnectionObserver = Observer<Unit> {
////
//    }
//    private val openDetailScreenObserver = Observer<NewsData> {
//        findNavController().navigate(
//            MainScreenDirections.actionMainScreenToDetailScreen(
//                it.title,
//                it.read_more
//            )
//        )
//    }
//    private val swipeObserver = Observer<Boolean> {
//        binding.swipeRefresh.isRefreshing = it
//    }
//    private val categoriesObserver = Observer<List<NewsResponse.CategoryAdapterData>> {
//        categoryAdapter.submitList(it)
//        binding.navigationLayout.listCategory.adapter = categoryAdapter
//        binding.navigationLayout.listCategory.layoutManager = LinearLayoutManager(requireContext())
//    }
//    private val categoryTitleObserver = Observer<String> {
//        binding.categoryTitle.text = it
//    }
//
//    private val newsObserver = Observer<List<NewsData>> {
//        adapter.submitList(it)
//        binding.listRV.adapter = adapter
//        binding.listRV.layoutManager = LinearLayoutManager(requireContext())
//    }
}