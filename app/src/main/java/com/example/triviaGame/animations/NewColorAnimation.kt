package com.example.triviaGame.animations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaGame.HomepageActivity
import com.example.triviaGame.R
import com.example.triviaGame.sharedPreferences
import com.example.triviaGame.themeKey
import kotlinx.android.synthetic.main.activity_new_color.*

class NewColorAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color  = intent?.extras?.get("color").toString()
        val to_show = "New theme: $color"


        sharedPreferences = getSharedPreferences(
            "ThemePref",
            Context.MODE_PRIVATE
        )

        when (sharedPreferences.getString(themeKey, "red")) {
            "lime" ->  theme.applyStyle(R.style.OverlayThemeLime, true)
            "red" ->  theme.applyStyle(R.style.OverlayThemeRed, true)
            "green" ->  theme.applyStyle(R.style.OverlayThemeGreen, true)
            "blue" ->  theme.applyStyle(R.style.OverlayThemeBlue, true)
        }

        setContentView(R.layout.activity_new_color)

        tvColorSelected.setText(to_show)
        tvColorSelected.alpha = 0f
        tvColorSelected.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
            finish()
        }

    }
}