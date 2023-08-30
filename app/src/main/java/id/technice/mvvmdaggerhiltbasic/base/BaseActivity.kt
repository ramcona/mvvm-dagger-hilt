package id.technice.mvvmdaggerhiltbasic.base

import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import id.technice.mvvmdaggerhiltbasic.R
import id.technice.mvvmdaggerhiltbasic.databinding.LayoutToolbarBinding
import id.technice.mvvmdaggerhiltbasic.sharedView.InformationDialog
import id.technice.mvvmdaggerhiltbasic.sharedView.LoadingDialog


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    fun removeActionBar() {
        supportActionBar?.hide()

        val window: Window = window
        val decorView: View = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)


        wic.isAppearanceLightStatusBars = false  // true or false as desired.
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

    }


    fun setToolbar(title: String, toolbarTitle: LayoutToolbarBinding, menuCallback: MenuCallback? = null, isWhite:Boolean = false) {
        removeActionBar()
        if (menuCallback != null){
            toolbarTitle.toolbarMenu.visibility = View.VISIBLE
            toolbarTitle.toolbarMenu.setOnClickListener {
                menuCallback.onClicked()
            }
        }else{
            toolbarTitle.toolbarMenu.visibility = View.GONE
        }

        if(isWhite) {
            toolbarTitle.toolbarBack.setImageResource(R.drawable.ic_back_white)
            toolbarTitle.toolbarTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        toolbarTitle.toolbarTitle.text = title
        toolbarTitle.toolbarBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //mengubah warna status bar
    }

    fun removeLoading() {
        App.loadingDialog.dismiss()
    }

    fun showLoading() {
        App.loadingDialog = LoadingDialog(this)

        App.loadingDialog.show()
    }

    fun showMessageDialog(title:String, message:String) {
        InformationDialog(this, title, message) {

        }
    }
}

interface MenuCallback {
    fun onClicked()
}