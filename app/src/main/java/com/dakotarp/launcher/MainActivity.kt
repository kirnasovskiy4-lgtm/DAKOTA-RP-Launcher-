package com.dakotarp.launcher

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    private val dark = Color.rgb(13, 14, 20)
    private val purple = Color.rgb(125, 65, 220)
    private val white = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = dark
        window.navigationBarColor = dark

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 24, 20, 24)
            setBackgroundColor(dark)
        }

        val scroll = android.widget.ScrollView(this)
        scroll.addView(root)

        val logo = text("DAKOTA RP", 30, purple, true)
        logo.gravity = Gravity.CENTER
        root.addView(logo)

        val subtitle = text("ROLEPLAY • MOBILE LAUNCHER", 12, Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER
        root.addView(subtitle)

        addSpace(root, 24)

        // TELEGRAM
        val telegram = card(root, Color.rgb(35, 75, 125))
        telegram.addView(text("✈ TELEGRAM DAKOTA RP", 20, white, true))
        telegram.addView(text(
            "Новини, оновлення та спілкування",
            13, white
        ))

        val telegramButton = button("ПЕРЕЙТИ В TELEGRAM")
        telegram.addView(telegramButton)

        telegramButton.setOnClickListener {
            openLink("https://t.me/+rGdQ8ui9egg1YWYy")
        }
        telegram.setOnClickListener {
            openLink("https://t.me/+rGdQ8ui9egg1YWYy")
        }

        addSpace(root, 16)

        // TIKTOK
        val tiktok = card(root, Color.rgb(55, 35, 65))
        tiktok.addView(text("♪ TIKTOK DAKOTA RP", 20, white, true))
        tiktok.addView(text(
            "Відео та новини нашого проєкту",
            13, white
        ))
        tiktok.addView(text(
            "ПОСИЛАННЯ ДОДАМО ПІЗНІШЕ",
            11, Color.LTGRAY, true
        ))

        addSpace(root, 25)

        root.addView(text("СПИСОК СЕРВЕРІВ", 21, white, true))
        addSpace(root, 12)

        // SERVER
        val server = card(root, Color.rgb(30, 30, 42))
        server.addView(text("DAKOTA RP", 22, purple, true))
        server.addView(text("SA-MP • Android", 13, Color.LTGRAY))
        server.addView(text(
            "188.127.241.74:3015",
            15, white, true
        ))

        val play = button("ЗАЙТИ НА СЕРВЕР")
        server.addView(play)

        play.setOnClickListener {
            Toast.makeText(
                this,
                "Підключення SA-MP клієнта ще налаштовується",
                Toast.LENGTH_LONG
            ).show()
        }

        addSpace(root, 25)

        val footer = text(
            "DAKOTA RP • OFFICIAL LAUNCHER",
            11, Color.GRAY
        )
        footer.gravity = Gravity.CENTER
        root.addView(footer)

        setContentView(scroll)
    }

    private fun text(
        value: String,
        size: Int,
        color: Int,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size.toFloat()
            setTextColor(color)
            if (bold) setTypeface(null, Typeface.BOLD)
            setPadding(0, 7, 0, 7)
        }
    }

    private fun card(
        parent: LinearLayout,
        color: Int
    ): LinearLayout {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 18, 18, 18)
            background = GradientDrawable().apply {
                setColor(color)
                cornerRadius = 24f
            }
        }

        parent.addView(
            layout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        return layout
    }

    private fun button(label: String): Button {
        return Button(this).apply {
            text = label
            textSize = 14f
            setTextColor(white)
            setBackgroundColor(purple)
        }
    }

    private fun addSpace(
        parent: LinearLayout,
        height: Int
    ) {
        val space = android.view.View(this)
        parent.addView(
            space,
            LinearLayout.LayoutParams(1, height)
        )
    }

    private fun openLink(url: String) {
        try {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            )
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Не вдалося відкрити посилання",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
