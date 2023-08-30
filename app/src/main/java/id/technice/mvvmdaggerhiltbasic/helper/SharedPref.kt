package id.technice.mvvmdaggerhiltbasic.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import id.technice.mvvmdaggerhiltbasic.data.model.User

class SharedPref(context : Context) {

    private val mypref = "MAIN_PREF"
    private val sp: SharedPreferences = context.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sp.edit()
    private val onReview = "onReview"
    private val isLogin = "isLogin"
    private val showRating = "showRating"
    private val openApp = "openApp"

    private val songPlay = "songPlay"

    private val notifShowroom = "notifShowroom"
    private val notifNews = "notifNews"
    private val notifMng = "notifMng"
    private val notifEvent = "notifEvent"
    private val notifHandshake = "notifHandshake"

    private val accessToken = "accessToken"
    private val refreshToken = "refreshToken"
    private val users = "users"

    fun setOnReview(value: Boolean) {
        editor.putBoolean(onReview, value)
        editor.commit()
        editor.apply()
    }


    fun setUser(data: User): User {
        val json = Gson().toJson(data, User::class.java)
        sp.edit().putString(users, json).apply()
        return getUser()
    }

    fun getUser(): User {
        val data = sp.getString(users, null) ?: return User()
        return Gson().fromJson(data, User::class.java)
    }

    fun getOnReview(): Boolean {
        return sp.getBoolean(onReview, false)
    }

    fun setOpenApp(value : Int){
        sp.edit().putInt(openApp, value).apply()
    }

    fun setIsLogin(value : Boolean){
        sp.edit().putBoolean(isLogin, value).apply()
    }

    fun getIsLogin(): Boolean {
        return sp.getBoolean(isLogin, false)
    }

    fun getOpenApp() : Int{
        return sp.getInt(openApp, 0)
    }

    fun setRated(value : Boolean){
        sp.edit().putBoolean(showRating, value).apply()
    }

    fun isRated() : Boolean{
        return sp.getBoolean(showRating, false)
    }

    fun setAccessToken(value : String){
        sp.edit().putString(accessToken, value).apply()
    }

    fun getAccessToken(): String {
        return sp.getString(accessToken, "") ?: ""
    }

    fun setRefreshToken(value : String){
        sp.edit().putString(refreshToken, value).apply()
    }

    fun getRefreshToken(): String {
        return sp.getString(refreshToken, "") ?: ""
    }

    fun clearAll() {
        editor.clear()
        editor.commit()
    }

}