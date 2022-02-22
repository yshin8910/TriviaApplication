package com.example.triviaGame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.blue
import com.example.triviaGame.MultipleChoiceActivity
import com.example.triviaGame.R
import com.example.triviaGame.animations.NewColorAnimation
import kotlinx.android.synthetic.main.activity_selected_correct.*
import kotlinx.android.synthetic.main.activity_selected_correct.btnGoHome
import kotlinx.android.synthetic.main.activity_selected_correct.btnNextQuestion
import kotlinx.android.synthetic.main.activity_selected_correct.tvQuestion
import kotlinx.android.synthetic.main.activity_selected_wrong.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        setContentView(R.layout.activity_settings)
        btnBackgroundLime.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "lime").apply()
            val intent = Intent(this.applicationContext, NewColorAnimation::class.java) // from getIntent()
            intent.putExtra("color", "lime")
            startActivity(intent)
            finish()
        }

        btnBackgroundBlue.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "blue").apply()
            val intent = Intent(this.applicationContext, NewColorAnimation::class.java) // from getIntent()
            intent.putExtra("color", "blue")
            startActivity(intent)
            finish()
        }

        btnBackgroundGreen.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "green").apply()
            val intent = Intent(this.applicationContext, NewColorAnimation::class.java) // from getIntent()
            intent.putExtra("color", "green")
            startActivity(intent)
            finish()
        }

        btnBackgroundRed.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "red").apply()
            val intent = Intent(this.applicationContext, NewColorAnimation::class.java) // from getIntent()
            intent.putExtra("color", "red")
            startActivity(intent)
            finish()
        }

        btnGoHome.setOnClickListener {
            val intent = Intent(this.applicationContext, HomepageActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}


