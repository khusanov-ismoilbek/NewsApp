package uz.gita.newsappflowretrofitdagger.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappflowretrofitdagger.R
import uz.gita.newsappflowretrofitdagger.data.source.local.entity.NewsEntity
import uz.gita.newsappflowretrofitdagger.databinding.ItemNewsBinding

class NewsAdapter : ListAdapter<NewsEntity, NewsAdapter.NewsViewHolder>(MyDiffUtil) {

    private var onClickItemListener: ((NewsEntity) -> Unit)? = null

    private object MyDiffUtil : DiffUtil.ItemCallback<NewsEntity>() {
        override fun areItemsTheSame(
            oldItem: NewsEntity,
            newItem: NewsEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: NewsEntity,
            newItem: NewsEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickItemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            binding.author.text = getItem(absoluteAdapterPosition).author
            binding.title.text = getItem(absoluteAdapterPosition).title
            binding.description.text = getItem(absoluteAdapterPosition).description

            Glide.with(binding.image)
                .load(getItem(absoluteAdapterPosition).image)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_error)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(ItemNewsBinding.bind(view))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickItemListener(block: (NewsEntity) -> Unit) {
        onClickItemListener = block
    }
}