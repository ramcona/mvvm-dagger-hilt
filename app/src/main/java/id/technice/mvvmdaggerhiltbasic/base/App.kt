package id.technice.mvvmdaggerhiltbasic.base

import android.app.Application
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.view.View
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.HiltAndroidApp
import id.technice.mvvmdaggerhiltbasic.data.model.User
import id.technice.mvvmdaggerhiltbasic.helper.Helper
import id.technice.mvvmdaggerhiltbasic.helper.SharedPref
import id.technice.mvvmdaggerhiltbasic.sharedView.LoadingDialog
import java.util.Calendar

@HiltAndroidApp
class App:Application() {
    companion object {
        lateinit var pref: SharedPref
        lateinit var user: User

        var helper = Helper

        var accessToken = ""
        var refreshToken = ""
        lateinit var app:App
        lateinit var loadingDialog:LoadingDialog

    }

    fun clearAppData(){
        pref.clearAll()

        user = User()
    }



    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        pref = SharedPref(this)
        updateUserData()
        app = this
        loadingDialog = LoadingDialog(this)

    }

    fun updateUserData(){
        accessToken = pref.getAccessToken()
        refreshToken = pref.getRefreshToken()
        user = pref.getUser()
    }

    fun showSnackbar(view: View, message:String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

}