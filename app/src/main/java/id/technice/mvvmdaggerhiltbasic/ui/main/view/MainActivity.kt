package id.technice.mvvmdaggerhiltbasic.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.technice.mvvmdaggerhiltbasic.base.BaseActivity
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.databinding.ActivityMainBinding
import id.technice.mvvmdaggerhiltbasic.helper.viewBinding
import id.technice.mvvmdaggerhiltbasic.networkUtils.Status
import id.technice.mvvmdaggerhiltbasic.ui.main.adapter.NewsAdapter
import id.technice.mvvmdaggerhiltbasic.ui.main.viewModel.NewsViewModel

class MainActivity : BaseActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupUI()
        setupObserver()
        action()

    }

    private fun action() {
        binding.swipe.setOnRefreshListener {
            newsViewModel.fetchNews()

            binding.swipe.isRefreshing = false
        }
    }

    private fun setupUI() {
        removeActionBar()
        binding.rvData.layoutManager = LinearLayoutManager(this)

        binding.rvData.addItemDecoration(
            DividerItemDecoration(
                binding.rvData.context,
                (binding.rvData.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvData.adapter = newsAdapter
    }

    private fun setupObserver() {
        newsViewModel.listNews.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    it.data?.let { datas ->
                        renderList(datas)
                    }
                    binding.rvData.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvData.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }

    private fun renderList(users: List<News>) {
        newsAdapter.addData(users)
        newsAdapter.notifyDataSetChanged()
    }

}