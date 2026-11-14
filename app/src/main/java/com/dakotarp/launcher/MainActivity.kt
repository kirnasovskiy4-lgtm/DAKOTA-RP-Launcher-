package com.dakotarp.launcher

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast

class MainActivity : Activity() {

    private val bg = Color.rgb(7, 8, 12)
    private val panel = Color.rgb(17, 19, 25)
    private val red = Color.rgb(235, 35, 55)
    private val silver = Color.rgb(205, 211, 222)
    private val white = Color.WHITE
    private val muted = Color.rgb(135, 143, 157)

    private val serverAddress = "188.127.241.74:3015"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        val scroll = ScrollView(this).apply {
            setBackgroundColor(bg)
            isFillViewport = true
        }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(24), dp(20), dp(30))
            setBackgroundColor(bg)
        }

        scroll.addView(root)

        // HEADER
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val logo = ImageView(this).apply {
            setImageResource(
                resources.getIdentifier(
                    "dakota_logo",
                    "drawable",
                    packageName
                )
            )
            scaleType = ImageView.ScaleType.FIT_CENTER
        }

        val logoId = resources.getIdentifier(
            "dakota_logo",
            "drawable",
            packageName
        )

        if (logoId != 0) {
            header.addView(
                logo,
                LinearLayout.LayoutParams(dp(58), dp(58))
            )
        }

        val brand = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(10), 0, 0, 0)
        }

        brand.addView(text("DAKOTA", 25, white, true))
        brand.addView(text("ROLEPLAY", 10, red, true))
        header.addView(brand)

        root.addView(header)

        addText(root, "MOBILE GAME LAUNCHER", 10, muted, true)
        addSpace(root, 25)

        // HERO
        val hero = card(
            root,
            intArrayOf(
                Color.rgb(49, 10, 18),
                panel
            ),
            red
        )

        addText(hero, "●  OFFICIAL PROJECT", 10, red, true)
        addSpace(hero, 15)

        addText(hero, "ТВОЯ ІСТОРІЯ", 28, white, true)
        addText(hero, "ПОЧИНАЄТЬСЯ ТУТ", 23, red, true)

        addSpace(hero, 12)

        addText(
            hero,
            "Створи власного персонажа, знайди друзів, " +
                "побудуй кар'єру та стань частиною DAKOTA RP.",
            13,
            silver
        )

        addSpace(hero, 18)

        addButton(hero, "ВІДКРИТИ САЙТ") {
            openLink("https://kirnasovskiy4-lgtm.github.io/DAKOTA-RP/")
        }

        addSpace(root, 25)

        // SERVER
        addText(root, "01 / GAME SERVERS", 10, red, true)
        addText(root, "ІГРОВИЙ СЕРВЕР", 24, white, true)

        addSpace(root, 10)

        val server = card(
            root,
            intArrayOf(
                Color.rgb(30, 32, 41),
                panel
            ),
            Color.rgb(75, 80, 94)
        )

        addText(server, "DAKOTA RP", 24, white, true)
        addText(server, "SA-MP • ANDROID", 10, red, true)

        addSpace(server, 18)

        addText(server, "АДРЕСА СЕРВЕРА", 10, muted, true)
        addText(server, serverAddress, 17, silver, true)

        addSpace(server, 10)

        addButton(server, "КОПІЮВАТИ IP") {
            copyServerAddress()
            Toast.makeText(
                this,
                "IP сервера скопійовано",
                Toast.LENGTH_SHORT
            ).show()
        }

        addButton(server, "ЗАЙТИ НА СЕРВЕР") {
            launchSamp()
        }

        addSpace(root, 25)

        // SOCIALS
        addText(root, "02 / COMMUNITY", 10, red, true)
        addText(root, "НАШІ СОЦІАЛЬНІ МЕРЕЖІ", 21, white, true)

        addSpace(root, 10)

        val telegram = card(
            root,
            intArrayOf(
                Color.rgb(20, 25, 34),
                panel
            ),
            Color.rgb(60, 75, 95)
        )

        addText(telegram, "TELEGRAM", 19, silver, true)
        addText(
            telegram,
            "Новини, оновлення та життя проєкту.",
            12,
            muted
        )

        addButton(telegram, "ВІДКРИТИ TELEGRAM") {
            openLink("https://t.me/+rGdQ8ui9egg1YWYy")
        }

        val tiktok = card(
            root,
            intArrayOf(
                Color.rgb(25, 25, 30),
                panel
            ),
            Color.rgb(70, 70, 80)
        )

        addText(tiktok, "TIKTOK", 19, silver, true)
        addText(
            tiktok,
            "Відео та новини нашого проєкту.",
            12,
            muted
        )
        addSpace(tiktok, 5)
        addText(tiktok, "СКОРО БУДЕ ДОСТУПНО", 10, red, true)

        addSpace(root, 28)

        addText(root, "DAKOTA RP • MOBILE LAUNCHER", 10, muted, true)
        addText(root, "© 2026 DAKOTA RP", 10, muted)

        setContentView(scroll)
    }

    private fun launchSamp() {
        copyServerAddress()

        val intent = Intent(Intent.ACTION_MAIN).addCategory(
            Intent.CATEGORY_LAUNCHER
        )

        val apps = packageManager.queryIntentActivities(intent, 0)

        val samp = apps.firstOrNull {
            val name = it.loadLabel(packageManager).toString()
            name.contains("SA-MP", true) ||
                name.contains("SAMP", true)
        }

        if (samp == null) {
            Toast.makeText(
                this,
                "SA-MP Android не знайдено",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        try {
            val launchIntent = packageManager.getLaunchIntentForPackage(
                samp.activityInfo.packageName
            )

            if (launchIntent == null) {
                Toast.makeText(
                    this,
                    "Не вдалося відкрити SA-MP",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            startActivity(launchIntent)

            Toast.makeText(
                this,
                "IP скопійовано. За потреби встав його в клієнті.",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Помилка запуску SA-MP",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun copyServerAddress() {
        val clipboard = getSystemService(CLIPBOARD_SERVICE)
                as ClipboardManager

        clipboard.setPrimaryClip(
            ClipData.newPlainText("DAKOTA RP", serverAddress)
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
            typeface = Typeface.create(
                "sans-serif",
                if (bold) Typeface.BOLD else Typeface.NORMAL
            )
            includeFontPadding = false
            setPadding(0, dp(5), 0, dp(5))
        }
    }

    private fun addText(
        parent: LinearLayout,
        value: String,
        size: Int,
        color: Int,
        bold: Boolean = false
    ) {
        parent.addView(text(value, size, color, bold))
    }

    private fun card(
        parent: LinearLayout,
        colors: IntArray,
        borderColor: Int
    ): LinearLayout {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))

            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors
            ).apply {
                cornerRadius = dp(15).toFloat()
                setStroke(dp(1), borderColor)
            }
        }

        parent.addView(
            layout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = dp(12)
            }
        )

        return layout
    }

    private fun addButton(
        parent: LinearLayout,
        label: String,
        action: () -> Unit
    ) {
        val button = TextView(this).apply {
            text = label
            textSize = 13f
            gravity = Gravity.CENTER
            setTextColor(white)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(dp(14), dp(15), dp(14), dp(15))

            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.rgb(145, 15, 33),
                    red
                )
            ).apply {
                cornerRadius = dp(9).toFloat()
                setStroke(dp(1), Color.rgb(255, 85, 100))
            }

            setOnClickListener { action() }
        }

        parent.addView(
            button,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = dp(10)
            }
        )
    }

    private fun addSpace(parent: LinearLayout, height: Int) {
        parent.addView(
            android.view.View(this),
            LinearLayout.LayoutParams(1, dp(height))
        )
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}
