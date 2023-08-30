package id.technice.mvvmdaggerhiltbasic.data.repository

import id.technice.mvvmdaggerhiltbasic.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}