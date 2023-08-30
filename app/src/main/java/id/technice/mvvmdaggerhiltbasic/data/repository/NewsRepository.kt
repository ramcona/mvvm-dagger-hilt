package id.technice.mvvmdaggerhiltbasic.data.repository

import id.technice.mvvmdaggerhiltbasic.data.api.ApiHelper
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getDetail(id:String) = apiHelper.getDetailNews(id)
    suspend fun getNews() = apiHelper.getNews()

}