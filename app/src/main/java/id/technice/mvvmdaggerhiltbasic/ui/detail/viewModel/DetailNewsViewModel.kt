package id.technice.mvvmdaggerhiltbasic.ui.detail.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.data.repository.NewsRepository
import id.technice.mvvmdaggerhiltbasic.networkUtils.NetworkHelper
import id.technice.mvvmdaggerhiltbasic.networkUtils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailNewsViewModel @Inject constructor(
    private val mainRepository: NewsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _news = MutableLiveData<Resource<News>>()
    val news: LiveData<Resource<News>>
        get() = _news

    init {
//        fetchNews()
    }

    fun fetchNews(id:String) {
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getDetail(id).let {
                    if (it.isSuccessful) {
                        it.body().let { body ->
                            body.let {
                                _news.postValue(Resource.success(body?.data))
                            }
                        }

                    } else _news.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _news.postValue(Resource.error("No internet connection", null))
        }
    }
}
