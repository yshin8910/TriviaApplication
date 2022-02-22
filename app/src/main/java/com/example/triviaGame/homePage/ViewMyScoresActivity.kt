package com.example.triviaGame


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaGame.R
import com.example.triviaGame.database.TriviaDatabase
import com.example.triviaGame.database.TriviaViewModel
import kotlinx.android.synthetic.main.activity_view_my_scores.*

class ViewMyScoresActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_view_my_scores)

        val dataSource = TriviaDatabase.getInstance(this).TriviaDao
        var triviaViewModel = TriviaViewModel(dataSource, this.application)

        var account = triviaViewModel.getCurrentUser()


        btnReturn.setOnClickListener {
            finish()
        }

        tvHealthcareScore.setText("${account.healthScore}")
        tvCSScore.setText("${account.securityScore}")
        tvFinancesScore.setText("${account.financialScore}")
    }
}