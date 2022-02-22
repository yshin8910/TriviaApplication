package com.example.triviaGame.homePage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R
import com.example.triviaGame.sharedPreferences
import com.example.triviaGame.themeKey

import kotlinx.android.synthetic.main.activity_view_leaderboard.*
import kotlinx.android.synthetic.main.activity_view_leaderboard.btnReturn
import kotlinx.android.synthetic.main.activity_view_my_scores.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class ViewLeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_leaderboard)

        val initialFragment = LeaderboardFragment.newInstance("Healthcare")
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment, initialFragment)
        trans.commit()

        btnShowHealthcare.setOnClickListener {
            val healthcareFragment = LeaderboardFragment.newInstance("Healthcare")
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragment, healthcareFragment)
            trans.commit()
        }

        btnShowCS.setOnClickListener {
            val healthcareFragment = LeaderboardFragment.newInstance("CS")
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragment, healthcareFragment)
            trans.commit()
        }

        btnShowFinances.setOnClickListener {
            val healthcareFragment = LeaderboardFragment.newInstance("Finances")
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragment, healthcareFragment)
            trans.commit()
        }


        btnReturn.setOnClickListener {
            finish()
        }
    }
}