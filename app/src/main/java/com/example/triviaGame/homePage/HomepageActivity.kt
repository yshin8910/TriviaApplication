package com.example.triviaGame


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaGame.R
import com.example.triviaGame.database.GlobalUser
import com.example.triviaGame.homePage.ViewLeaderboardActivity
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_main_screen_animation.*

class HomepageActivity : AppCompatActivity() {
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

        setContentView(R.layout.activity_homepage)

        btnHealthcare.setOnClickListener {
            val intent = Intent(this.applicationContext, MultipleChoiceActivity::class.java)
            intent.putExtra("category", "Healthcare")
            startActivity(intent)
        }

        btnFinances.setOnClickListener {
            val intent = Intent(this.applicationContext, MultipleChoiceActivity::class.java)
            intent.putExtra("category", "Finances")
            startActivity(intent)
        }

        btnCompScience.setOnClickListener {
            val intent = Intent(this.applicationContext, MultipleChoiceActivity::class.java)
            intent.putExtra("category", "CS")
            startActivity(intent)
        }

        btnViewScores.setOnClickListener {
            val intent = Intent(this.applicationContext, ViewMyScoresActivity::class.java)
            startActivity(intent)
        }

        btnViewLeaderboard.setOnClickListener {
            val intent = Intent(this.applicationContext, ViewLeaderboardActivity::class.java)
            startActivity(intent)
        }

        btnSettings.setOnClickListener {
            val intent = Intent(this.applicationContext, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogout.setOnClickListener {
            GlobalUser.user = null
            finish()
        }

    }
}