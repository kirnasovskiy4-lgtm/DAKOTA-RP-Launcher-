package com.dakotarp.launcher

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {
    private val orange = Color.rgb(255, 130, 20)
    private val dark = Color.rgb(13, 14, 18)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = dark
        window.navigationBarColor = dark

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 32, 28, 32)
            setBackgroundColor(dark)
        }

        val logo = TextView(this).apply {
            text = "DAKOTA RP"
            textSize = 34f
            setTextColor(orange)
            gravity = Gravity.CENTER
        }
        root.addView(logo)

        val subtitle = TextView(this).apply {
            text = "ROLEPLAY • ANDROID LAUNCHER"
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 28)
        }
        root.addView(subtitle)

        val status = TextView(this).apply {
            text = "ПРОЄКТ У РОЗРОБЦІ"
            textSize = 14f
            setTextColor(orange)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }
        root.addView(status)

        val play = Button(this).apply {
            text = "ЗАЙТИ НА СЕРВЕР"
            setTextColor(Color.BLACK)
            setBackgroundColor(orange)
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "SA-MP Mobile ще не підключено. Налаштуємо клієнт на наступному етапі.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        root.addView(play, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val news = TextView(this).apply {
            text = "\nНОВИНИ\nСлідкуй за розвитком DAKOTA RP.\n\nTelegram: t.me/rGdQ8ui9egg1YWYy"
            textSize = 15f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 28, 0, 0)
        }
        root.addView(news)

        setContentView(root)
    }
}
