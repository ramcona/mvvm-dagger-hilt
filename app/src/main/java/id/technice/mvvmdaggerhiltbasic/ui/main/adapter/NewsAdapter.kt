package id.technice.mvvmdaggerhiltbasic.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.databinding.ItemNewsBinding
import id.technice.mvvmdaggerhiltbasic.extention.getTimeAgo
import id.technice.mvvmdaggerhiltbasic.extention.loadImage
import id.technice.mvvmdaggerhiltbasic.helper.Go
import id.technice.mvvmdaggerhiltbasic.ui.detail.view.DetailNewsActivity

class NewsAdapter(private var datas:List<News>): RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun addData(list: List<News>) {
        datas = list
    }

    class DataViewHolder(var item: ItemNewsBinding): RecyclerView.ViewHolder(item.root) {

        val context: Context = itemView.context

        fun setData(data: News) {
            item.tvTitle.text = data.title
            data.news_category?.let {
                item.tvCategory.text = it.title
            } ?: {
                item.tvCategory.text = "-"
            }
            item.tvDate.text = data.createdAt.getTimeAgo()
            item.tvThumb.loadImage(data.thumb)

            item.tvCategory.rootView.setOnClickListener {
                Go(context).move(DetailNewsActivity::class.java, id = data.id)
            }
        }
    }
}


