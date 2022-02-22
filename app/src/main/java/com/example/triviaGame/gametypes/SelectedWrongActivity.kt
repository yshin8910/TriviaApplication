package com.example.triviaGame.gametypes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaGame.MultipleChoiceActivity
import com.example.triviaGame.R
import kotlinx.android.synthetic.main.activity_selected_correct.*
import kotlinx.android.synthetic.main.activity_selected_correct.btnGoHome
import kotlinx.android.synthetic.main.activity_selected_correct.btnNextQuestion
import kotlinx.android.synthetic.main.activity_selected_correct.tvQuestion
import kotlinx.android.synthetic.main.activity_selected_wrong.*

class SelectedWrongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        theme.applyStyle(R.style.OverlayThemeRed, true)
        setContentView(R.layout.activity_selected_wrong)

        val category  = intent?.extras?.get("category").toString()
        val question  = intent?.extras?.get("question").toString()
        val correct_answer  = intent?.extras?.get("correct_answer").toString()
        val wrong_answer  = intent?.extras?.get("wrong_answer").toString()

        tvQuestion.setText(question)
        tvAnswerCorrect.setText(correct_answer)
        tvAnswerWrong.setText(wrong_answer)

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

