package id.technice.mvvmdaggerhiltbasic.helper

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


object Helper {

    fun String.toFormattedDate(to:String, from:String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String {
        val inputFormat = SimpleDateFormat(from, Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat(to, Locale.getDefault())
        try {
            val date = inputFormat.parse(this) ?: return this
            return  outputFormat.format(date)
        }catch (e: ParseException){
            return this
        }

    }


    fun toHexString(data: ByteArray): String{
        return data.asUByteArray().joinToString("") {
            it.toString(16).padStart(2, '0')
        }
    }

    fun fromHtml(source: String): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }


    //MAKE LOG RESUCE
    fun log(place: String = "", msg: String){
        //CHECK RELEASE MODE [if release log not show]

        if (!Validasi().isRelease()){
            if (place.isBlank()){
                Log.e("MGS", msg)
            }else{
                Log.e("MGS " + place, msg)
            }
        }

    }

    fun getCurrentDateTime():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    fun provideCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    fun provideTime():String{
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }


}