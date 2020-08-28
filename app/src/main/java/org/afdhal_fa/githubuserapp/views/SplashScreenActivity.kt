package org.afdhal_fa.githubuserapp.views

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.afdhal_fa.githubuserapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        actionBar?.hide()

        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(
                this,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                true
            )
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(
                this,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                false
            )
            window.statusBarColor = Color.TRANSPARENT
        }

        val welcomeScreenDisplay = 3000 // 3000 = 3 detik

        val welcomeThread: Thread = object : Thread() {
            var wait = 0
            override fun run() {
                try {
                    super.run()
                    while (wait < welcomeScreenDisplay) {
                        sleep(100)
                        wait += 100
                    }
                } catch (e: Exception) {
                    println("EXc=$e")
                } finally {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
            }
        }
        welcomeThread.start()

    }

    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val window = activity.window
        val winParams = window.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        window.attributes = winParams
    }
}