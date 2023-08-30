package id.technice.mvvmdaggerhiltbasic.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import id.technice.mvvmdaggerhiltbasic.BuildConfig
import java.util.ArrayList

class Validasi {

    fun isRelease(): Boolean {
        return false
    }

    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    fun validasiPhone(telp: String, code:String): String {

        var phonenum = telp
        phonenum = phonenum.replace(" ", "")
        phonenum = phonenum.replace("-", "")

        if (phonenum.contains("+")){
            return phonenum
        }

        phonenum = when {
            phonenum.startsWith("08") -> "+${code}" + phonenum.substring(1)
            phonenum.startsWith("8") -> "+${code}$phonenum"
            else -> "+${code}${phonenum}"
        }

        if (!phonenum.contains("+")){
            phonenum = "+$code$phonenum"
        }
        return phonenum
    }

    fun ijinKamera(context: Context): Boolean {

        if (Build.VERSION.SDK_INT >= 23){
            val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

            val listPermissionsNeed = ArrayList<String>()
            if (camera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.CAMERA)
            }
            if (!listPermissionsNeed.isEmpty()) {
                ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
                return false
            }
        }


        return true
    }

    fun ijinDokumen(context: Context): Boolean {

        if (Build.VERSION.SDK_INT >= 23){
            val r = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            val w = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            val listPermissionsNeed = ArrayList<String>()
            if (r != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (w != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (!listPermissionsNeed.isEmpty()) {
                ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
                return false
            }
        }


        return true
    }

    fun ijinDokumenAndCamera(context: Context): Boolean {

        if (Build.VERSION.SDK_INT >= 23){
            val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            val r = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            val w = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            val listPermissionsNeed = ArrayList<String>()
            if (camera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.CAMERA)
            }
            if (r != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (w != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeed.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (!listPermissionsNeed.isEmpty()) {
                ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
                return false
            }
        }


        return true
    }

}