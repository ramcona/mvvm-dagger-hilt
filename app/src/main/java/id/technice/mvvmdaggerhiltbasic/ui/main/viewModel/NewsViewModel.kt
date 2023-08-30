package id.technice.mvvmdaggerhiltbasic.ui.main.viewModel

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
class NewsViewModel @Inject constructor(
    private val mainRepository: NewsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _listNews = MutableLiveData<Resource<List<News>>>()
    val listNews: LiveData<Resource<List<News>>>
        get() = _listNews

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            _listNews.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getNews().let { it ->
                    if (it.isSuccessful) {
                        it.body().let { body ->
                            body.let {
                                Log.e("FFF", "${body}")
                                Log.e("FFF", "${body?.data?.data?.size}")
                                _listNews.postValue(Resource.success(body?.data?.data))
                            }
                        }
//                        Log.e("FFF", "${it.body()?.data?.data?.size}")
//                        Log.e("FFF", "${it.body()?.data?.data}")
//                        _listNews.postValue(Resource.success(it.body()?.data?.data))
                    } else _listNews.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _listNews.postValue(Resource.error("No internet connection", null))
        }
    }
}