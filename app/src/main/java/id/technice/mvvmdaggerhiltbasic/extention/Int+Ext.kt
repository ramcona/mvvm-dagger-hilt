package id.technice.mvvmdaggerhiltbasic.extention

import java.text.NumberFormat
import java.util.*

fun Int.formatRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this)
}