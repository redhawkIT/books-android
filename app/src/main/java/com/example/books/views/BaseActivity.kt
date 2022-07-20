package com.example.books.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressHUD: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressHUD = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }

    fun showProgressHUD() {
        progressHUD.show()
    }

    fun hideProgressHUD() {
        progressHUD.dismiss()
    }
}