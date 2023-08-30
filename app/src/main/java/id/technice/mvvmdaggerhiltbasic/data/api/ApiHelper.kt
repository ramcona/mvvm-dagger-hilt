package id.technice.mvvmdaggerhiltbasic.data.api

import id.technice.mvvmdaggerhiltbasic.base.BaseListResponse
import id.technice.mvvmdaggerhiltbasic.base.BaseResponse
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>

    /*news*/
    suspend fun getDetailNews(id:String): Response<BaseResponse<News>>
    suspend fun getNews(): Response<BaseListResponse<List<News>>>
}