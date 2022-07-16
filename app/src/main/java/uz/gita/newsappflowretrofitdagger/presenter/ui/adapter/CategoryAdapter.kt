package uz.gita.newsappflowretrofitdagger.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newsappflowretrofitdagger.R
import uz.gita.newsappflowretrofitdagger.data.source.remote.response.NewsResponse
import uz.gita.newsappflowretrofitdagger.databinding.ItemCategoryBinding

class CategoryAdapter: ListAdapter<NewsResponse.CategoryAdapterData, CategoryAdapter.MyViewHolder>(CategoryCallBack){

    private var onCategoryClickListener : ((String) -> Unit)? = null

    private object CategoryCallBack: DiffUtil.ItemCallback<NewsResponse.CategoryAdapterData>(){
        override fun areItemsTheSame(
            oldItem: NewsResponse.CategoryAdapterData,
            newsItem: NewsResponse.CategoryAdapterData
        ): Boolean {
            return oldItem.id == newsItem.id
        }

        override fun areContentsTheSame(
            oldItem: NewsResponse.CategoryAdapterData,
            newsItem: NewsResponse.CategoryAdapterData
        ): Boolean {
            return oldItem == newsItem
        }
    }

    inner class MyViewHolder(private val binding:
                             ItemCategoryBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener{
                onCategoryClickListener?.invoke(getItem(absoluteAdapterPosition).category)
            }
        }

            fun bind(){
                binding.textCategory.text = getItem(absoluteAdapterPosition).category
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(ItemCategoryBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }


    fun setOnCategoryClickListener(block : (String) -> Unit){
        onCategoryClickListener = block
    }
}