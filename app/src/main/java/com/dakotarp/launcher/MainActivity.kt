
package com.dakotarp.launcher

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.ComponentName
import android.net.Uri
import android.content.ClipData
import android.content.ClipboardManager
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val bg = Color.rgb(9, 11, 16)
    private val panel = Color.rgb(20, 25, 35)
    private val orange = Color.rgb(255, 107, 0)
    private val yellow = Color.rgb(255, 184, 0)
    private val white = Color.WHITE
    private val muted = Color.rgb(146, 154, 170)

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

        header.addView(text("DAKOTA", 27, orange, true))
        header.addView(text(" RP", 27, yellow, true))

        root.addView(header)

        addText(root, "ROLEPLAY • MOBILE LAUNCHER", 11, muted, true)
        addSpace(root, 25)

        // HERO
        val hero = card(
            root,
            intArrayOf(
                Color.rgb(40, 24, 13),
                panel
            ),
            Color.rgb(117, 64, 27)
        )

        addText(hero, "●  DAKOTA RP LAUNCHER", 11, yellow, true)
        addSpace(hero, 17)

        addText(hero, "ТВОЯ НОВА", 29, white, true)
        addText(hero, "ІСТОРІЯ ПОЧИНАЄТЬСЯ ТУТ", 24, orange, true)

        addSpace(hero, 12)

        addText(
            hero,
            "Створи власного персонажа, знайди друзів, " +
                "побудуй кар'єру та стань частиною DAKOTA RP.",
            13,
            Color.LTGRAY
        )

        addSpace(hero, 18)

        addButton(hero, "ВІДКРИТИ САЙТ") {
            openLink(
                "https://kirnasovskiy4-lgtm.github.io/DAKOTA-RP/"
            )
        }

        addSpace(root, 20)

        // DEVELOPMENT STATUS
        val status = card(
            root,
            intArrayOf(
                Color.rgb(36, 23, 14),
                panel
            ),
            Color.rgb(117, 64, 27)
        )

        addText(status, "🚧  ПРОЄКТ DAKOTA RP", 17, yellow, true)

        addText(
            status,
            "Лаунчер підключений до тестового клієнта. " +
                "Слідкуй за оновленнями проєкту.",
            12,
            muted
        )

        addSpace(root, 25)

        // SERVER SECTION
        addText(root, "01 / ІГРОВІ СЕРВЕРИ", 11, orange, true)
        addText(root, "ОБЕРИ СЕРВЕР", 25, white, true)

        addSpace(root, 12)

        val server = card(
            root,
            intArrayOf(
                Color.rgb(27, 33, 45),
                panel
            ),
            Color.rgb(48, 57, 74)
        )

        addText(server, "DAKOTA RP", 23, yellow, true)
        addText(server, "SA-MP • ANDROID", 11, muted, true)

        addSpace(server, 15)

        addText(server, "АДРЕС СЕРВЕРА", 10, muted, true)
        addText(server, serverAddress, 17, white, true)

        addSpace(server, 8)

        addButton(server, "КОПІЮВАТИ IP") {
            copyServerAddress()
            Toast.makeText(
                this,
                "Адресу сервера скопійовано",
                Toast.LENGTH_SHORT
            ).show()
        }

        addSpace(server, 10)

        addButton(server, "ЗАЙТИ НА СЕРВЕР") {
            launchSamp()
        }

        addSpace(root, 25)

        // SOCIAL NETWORKS
        addText(root, "02 / СОЦІАЛЬНІ МЕРЕЖІ", 11, orange, true)
        addText(root, "СЛІДКУЙ ЗА DAKOTA RP", 22, white, true)

        addSpace(root, 12)

        // TELEGRAM
        val telegram = card(
            root,
            intArrayOf(
                Color.rgb(22, 139, 197),
                Color.rgb(34, 158, 217)
            ),
            Color.rgb(34, 158, 217)
        )

        addText(telegram, "✈  TELEGRAM", 20, white, true)
        addText(
            telegram,
            "Наш канал. Новини та оновлення проєкту.",
            12,
            white
        )

        addSpace(telegram, 10)

        addButton(telegram, "ПЕРЕЙТИ В TELEGRAM") {
            openLink("https://t.me/+rGdQ8ui9egg1YWYy")
        }

        // TIKTOK
        val tiktok = card(
            root,
            intArrayOf(
                Color.rgb(23, 23, 23),
                Color.rgb(48, 48, 48)
            ),
            Color.rgb(68, 68, 68)
        )

        addText(tiktok, "♪  TIKTOK", 20, white, true)
        addText(
            tiktok,
            "Відео та новини нашого проєкту.",
            12,
            Color.LTGRAY
        )
        addSpace(tiktok, 5)
        addText(
            tiktok,
            "СКОРО БУДЕ ДОСТУПНО",
            10,
            yellow,
            true
        )

        addSpace(root, 25)

        // FOOTER
        addText(
            root,
            "DAKOTA RP • MOBILE LAUNCHER",
            11,
            muted,
            true
        )

        addText(
            root,
            "© 2026 DAKOTA RP",
            10,
            Color.GRAY
        )

        setContentView(scroll)
    }

    // FIND AND OPEN SA-MP
    private fun launchSamp() {

        copyServerAddress()

        val launcherIntent = Intent(
            Intent.ACTION_MAIN
        ).addCategory(
            Intent.CATEGORY_LAUNCHER
        )

        val apps: List<ResolveInfo> =
            packageManager.queryIntentActivities(
                launcherIntent,
                0
            )

        val samp = apps.firstOrNull {
            val appName = it.loadLabel(packageManager).toString()

            appName.contains("SA-MP", ignoreCase = true) ||
                appName.contains("SAMP", ignoreCase = true)
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
            val launchIntent = Intent(
                Intent.ACTION_MAIN
            ).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)

                component = ComponentName(
                    samp.activityInfo.packageName,
                    samp.activityInfo.name
                )

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            startActivity(launchIntent)

            Toast.makeText(
                this,
                "IP DAKOTA RP скопійовано. Якщо потрібно, встав його в клієнті.",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Не вдалося відкрити SA-MP",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // COPY SERVER IP
    private fun copyServerAddress() {
        val clipboard =
            getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.setPrimaryClip(
            ClipData.newPlainText(
                "DAKOTA RP",
                serverAddress
            )
        )
    }

    // OPEN LINKS
    private fun openLink(url: String) {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
            )
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Не вдалося відкрити посилання",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // CREATE TEXT
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

    // CREATE CARD
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
                cornerRadius = dp(13).toFloat()
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

    // CREATE BUTTON
    private fun addButton(
        parent: LinearLayout,
        label: String,
        action: () -> Unit
    ) {
        val button = TextView(this).apply {
            text = label
            textSize = 13f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(23, 16, 10))
            typeface = Typeface.DEFAULT_BOLD
            setPadding(dp(14), dp(15), dp(14), dp(15))

            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(orange, yellow)
            ).apply {
                cornerRadius = dp(8).toFloat()
            }

            setOnClickListener {
                action()
            }
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

    private fun addSpace(
        parent: LinearLayout,
        height: Int
    ) {
        val space = android.view.View(this)

        parent.addView(
            space,
            LinearLayout.LayoutParams(
                1,
                dp(height)
            )
        )
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}
