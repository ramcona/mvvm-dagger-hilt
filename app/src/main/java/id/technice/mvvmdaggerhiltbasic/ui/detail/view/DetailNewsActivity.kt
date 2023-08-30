package id.technice.mvvmdaggerhiltbasic.ui.detail.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.technice.mvvmdaggerhiltbasic.R
import id.technice.mvvmdaggerhiltbasic.base.BaseActivity
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.databinding.ActivityDetailNewsBinding
import id.technice.mvvmdaggerhiltbasic.extention.getTimeAgo
import id.technice.mvvmdaggerhiltbasic.extention.loadImage
import id.technice.mvvmdaggerhiltbasic.extention.parseHtml
import id.technice.mvvmdaggerhiltbasic.helper.Config.extra_id
import id.technice.mvvmdaggerhiltbasic.helper.ImageDialog
import id.technice.mvvmdaggerhiltbasic.helper.viewBinding
import id.technice.mvvmdaggerhiltbasic.networkUtils.Status
import id.technice.mvvmdaggerhiltbasic.ui.detail.viewModel.DetailNewsViewModel

class DetailNewsActivity : BaseActivity() {

    private val binding by viewBinding(ActivityDetailNewsBinding::inflate)
    private val viewModel: DetailNewsViewModel by viewModels()

    private var id = ""
    private var news: News? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar(getString(R.string.teks_informasi), binding.toolbar, isWhite = true)

        getIntentData()
        setupObserver()
        action()

        viewModel.fetchNews(id)
    }

    private fun action() {
        binding.ivThumb.setOnClickListener {
            news?.let { it1 -> ImageDialog(this, it1.thumb).show() }
        }
    }

    private fun getIntentData() {
        id = intent.getStringExtra(extra_id) ?: ""
    }

    private fun setupObserver() {
        viewModel.news.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    removeLoading()
                    it.data?.let { data ->
                        showData(data)
                    }
                }

                Status.LOADING -> {
                    showLoading()
                }

                Status.ERROR -> {
                    removeLoading()
                    //Handle Error
                    showMessageDialog(getString(R.string.teks_informasi), it.message ?: "Tidak dapat memuat data")

                }
            }
        }
    }

    private fun showData(data: News) {
        news = data
        binding.tvDate.text = data.createdAt.getTimeAgo()
        data.news_category?.let {
            binding.tvCategory.text = it.title
        } ?: run {
            binding.tvCategory.text = "-"
        }
        binding.tvTitle.text = data.title
        binding.tvDescription.text = data.content.parseHtml()
        binding.ivThumb.loadImage(data.thumb)
        binding.tvViews.text = getString(R.string.teks_views, data.views)

    }
}