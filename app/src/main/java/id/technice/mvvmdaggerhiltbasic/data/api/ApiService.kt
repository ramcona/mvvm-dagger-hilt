package id.technice.mvvmdaggerhiltbasic.data.api

import id.technice.mvvmdaggerhiltbasic.base.BaseListResponse
import id.technice.mvvmdaggerhiltbasic.base.BaseResponse
import id.technice.mvvmdaggerhiltbasic.data.model.News
import id.technice.mvvmdaggerhiltbasic.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    /*news*/
    @GET("news/{id}")
    suspend fun getDetailNews(
        @Path("id") id:String
    ): Response<BaseResponse<News>>

    @GET("news")
    suspend fun getNews(
    ): Response<BaseListResponse<List<News>>>
}