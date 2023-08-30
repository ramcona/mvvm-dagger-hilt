package id.technice.mvvmdaggerhiltbasic.data.api

import id.technice.mvvmdaggerhiltbasic.base.BaseListResponse
import id.technice.mvvmdaggerhiltbasic.base.BaseResponse
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.data.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

    /*news*/
    override suspend fun getDetailNews(id: String): Response<BaseResponse<News>>  = apiService.getDetailNews(id)
    override suspend fun getNews(): Response<BaseListResponse<List<News>>> = apiService.getNews()
}