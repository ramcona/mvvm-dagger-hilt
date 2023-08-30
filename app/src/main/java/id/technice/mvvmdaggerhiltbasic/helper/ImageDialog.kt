package id.technice.mvvmdaggerhiltbasic.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import id.technice.mvvmdaggerhiltbasic.R
import id.technice.mvvmdaggerhiltbasic.extention.loadImage

class ImageDialog(context: Context, path:String) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_image)

        val imageView = findViewById<ImageView>(R.id.iv_thumb)
        imageView.loadImage(path)

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        imageView.setOnClickListener { dismiss() }
    }
}