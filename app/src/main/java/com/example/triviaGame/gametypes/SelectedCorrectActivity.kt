package com.example.triviaGame.gametypes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.triviaGame.MultipleChoiceActivity
import com.example.triviaGame.R
import com.example.triviaGame.database.TriviaDao
import com.example.triviaGame.database.TriviaDatabase
import com.example.triviaGame.sharedPreferences
import com.example.triviaGame.themeKey
import com.example.triviaGame.database.TriviaViewModel
import kotlinx.android.synthetic.main.activity_selected_correct.*

class SelectedCorrectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        theme.applyStyle(R.style.OverlayThemeGreen, true)
        setContentView(R.layout.activity_selected_correct)

        val category  = intent?.extras?.get("category").toString()
        val question  = intent?.extras?.get("question").toString()
        val answer  = intent?.extras?.get("answer").toString()

        val dataSource = TriviaDatabase.getInstance(this).TriviaDao
        var TriviaViewModel = TriviaViewModel(dataSource, this.application)


        Log.d("SCORES","" + TriviaViewModel.getCurrentUser())
        Log.d("Categorytype", category)
        if (category.equals("CS")) {
            TriviaViewModel.addCSScore()
        } else if (category.equals("Healthcare")) {
            TriviaViewModel.addHealthcareScore()
        } else if (category.equals("Finances")) {
            TriviaViewModel.addFinanceScore()
        } else {
            Log.e("CAT", "Bad category: $category")
        }

        Log.d("SCORES AFTER","" + TriviaViewModel.getCurrentUser())

        tvQuestion.setText(question)
        tvAnswer.setText(answer)



        btnNextQuestion.setOnClickListener {
            val intent = Intent(this.applicationContext, MultipleChoiceActivity::class.java)
            intent.putExtra("category", category)
            startActivity(intent)
            this.finish()
        }

        btnGoHome.setOnClickListener {
            this.finish()
        }
    }

}