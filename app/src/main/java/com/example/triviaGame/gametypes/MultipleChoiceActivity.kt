package com.example.triviaGame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import com.example.triviaGame.R
import com.example.triviaGame.gametypes.SelectedCorrectActivity
import com.example.triviaGame.gametypes.SelectedWrongActivity
import kotlinx.android.synthetic.main.activity_multiple_choice.*
import java.lang.Exception

class MultipleChoiceActivity: AppCompatActivity() {
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
        setContentView(R.layout.activity_multiple_choice)

        val question_type = (0..2).random()
        val category = intent?.extras?.get("category").toString()
        var cat_str = "None"
        if (category.equals("CS")) {
            cat_str = "CS"
        } else if (category.equals("Healthcare")) {
            cat_str = "H"
        } else if (category.equals("Finances")) {
            cat_str = "F"
        }


        if (question_type < 2) {
            val q_num = (0..10).random()

            val q_to_get = cat_str + "Q" + q_num
            var correct_answer = "E"
            var correct_answer_string = "None"


            val resId = this.resources.getIdentifier(q_to_get, "string", packageName)
            val question_text = getString(resId)




            try {
                correct_answer = "A"
                btnOptionOne.setText(
                    this.resources.getIdentifier(
                        q_to_get + "ACorr",
                        "string",
                        packageName
                    )
                )
                btnOptionTwo.setText(
                    this.resources.getIdentifier(
                        q_to_get + "B",
                        "string",
                        packageName
                    )
                )
                btnOptionThree.setText(
                    this.resources.getIdentifier(
                        q_to_get + "C",
                        "string",
                        packageName
                    )
                )
                btnOptionFour.setText(
                    this.resources.getIdentifier(
                        q_to_get + "D",
                        "string",
                        packageName
                    )
                )
                correct_answer_string = btnOptionOne.text.toString()
                btnOptionOne.setOnClickListener {
                    val intent =
                        Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("answer", correct_answer_string)
                    intent.putExtra("question", question_text)
                    startActivity(intent)
                    this.finish()
                }
            } catch (e: Exception) {
                try {
                    correct_answer = "B"
                    btnOptionOne.setText(
                        this.resources.getIdentifier(
                            q_to_get + "A",
                            "string",
                            packageName
                        )
                    )
                    btnOptionTwo.setText(
                        this.resources.getIdentifier(
                            q_to_get + "BCorr",
                            "string",
                            packageName
                        )
                    )
                    btnOptionThree.setText(
                        this.resources.getIdentifier(
                            q_to_get + "C",
                            "string",
                            packageName
                        )
                    )
                    btnOptionFour.setText(
                        this.resources.getIdentifier(
                            q_to_get + "D",
                            "string",
                            packageName
                        )
                    )
                    correct_answer_string = btnOptionTwo.text.toString()
                    btnOptionTwo.setOnClickListener {
                        val intent =
                            Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                        intent.putExtra("category", category)
                        intent.putExtra("answer", correct_answer_string)
                        intent.putExtra("question", question_text)
                        startActivity(intent)
                        this.finish()
                    }
                } catch (e: Exception) {
                    try {
                        correct_answer = "C"
                        btnOptionOne.setText(
                            this.resources.getIdentifier(
                                q_to_get + "A",
                                "string",
                                packageName
                            )
                        )
                        btnOptionTwo.setText(
                            this.resources.getIdentifier(
                                q_to_get + "B",
                                "string",
                                packageName
                            )
                        )
                        btnOptionThree.setText(
                            this.resources.getIdentifier(
                                q_to_get + "CCorr",
                                "string",
                                packageName
                            )
                        )
                        btnOptionFour.setText(
                            this.resources.getIdentifier(
                                q_to_get + "D",
                                "string",
                                packageName
                            )
                        )
                        correct_answer_string = btnOptionThree.text.toString()
                        btnOptionThree.setOnClickListener {
                            val intent =
                                Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                            intent.putExtra("category", category)
                            intent.putExtra("answer", correct_answer_string)
                            intent.putExtra("question", question_text)
                            startActivity(intent)
                            this.finish()
                        }
                    } catch (e: Exception) {
                        correct_answer = "D"
                        btnOptionOne.setText(
                            this.resources.getIdentifier(
                                q_to_get + "A",
                                "string",
                                packageName
                            )
                        )
                        btnOptionTwo.setText(
                            this.resources.getIdentifier(
                                q_to_get + "B",
                                "string",
                                packageName
                            )
                        )
                        btnOptionThree.setText(
                            this.resources.getIdentifier(
                                q_to_get + "C",
                                "string",
                                packageName
                            )
                        )
                        btnOptionFour.setText(
                            this.resources.getIdentifier(
                                q_to_get + "DCorr",
                                "string",
                                packageName
                            )
                        )
                        correct_answer_string = btnOptionFour.text.toString()
                        btnOptionFour.setOnClickListener {
                            val intent =
                                Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                            intent.putExtra("category", category)
                            intent.putExtra("answer", correct_answer_string)
                            intent.putExtra("question", question_text)
                            startActivity(intent)
                            this.finish()
                        }
                    }
                }
            }

            if (correct_answer != "A") {
                btnOptionOne.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("correct_answer", correct_answer_string)
                    intent.putExtra("wrong_answer", btnOptionOne.text.toString())
                    startActivity(intent)
                    this.finish()
                }
            }

            if (correct_answer != "B") {
                btnOptionTwo.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("correct_answer", correct_answer_string)
                    intent.putExtra("wrong_answer", btnOptionTwo.text.toString())
                    startActivity(intent)
                    this.finish()
                }
            }

            if (correct_answer != "C") {
                btnOptionThree.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("correct_answer", correct_answer_string)
                    intent.putExtra("wrong_answer", btnOptionThree.text.toString())
                    startActivity(intent)
                    this.finish()
                }
            }

            if (correct_answer != "D") {
                btnOptionFour.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("correct_answer", correct_answer_string)
                    intent.putExtra("wrong_answer", btnOptionFour.text.toString())
                    startActivity(intent)
                    this.finish()
                }
            }

            tvQuestion.setText(question_text)

        } else {
            val q_num = (11..15).random()

            val q_to_get = cat_str + "Q" + q_num
            var correct_answer = "E"
            var correct_answer_string = "None"


            val resId = this.resources.getIdentifier(q_to_get, "string", packageName)
            val question_text = getString(resId)

            btnOptionThree.visibility = View.INVISIBLE
            btnOptionFour.visibility = View.INVISIBLE

            tvQuestion.setText(question_text)



            btnOptionOne.setOnClickListener {
                val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                intent.putExtra("category", category)
                intent.putExtra("question", question_text)
                intent.putExtra("correct_answer", "False")
                intent.putExtra("wrong_answer","True")
                startActivity(intent)
                this.finish()
            }

            btnOptionTwo.setOnClickListener {
                val intent = Intent(this.applicationContext, SelectedWrongActivity::class.java)
                intent.putExtra("category", category)
                intent.putExtra("question", question_text)
                intent.putExtra("correct_answer", "True")
                intent.putExtra("wrong_answer","False")
                startActivity(intent)
                this.finish()
            }


            try {
                btnOptionOne.setText(
                    this.resources.getIdentifier(
                        q_to_get + "TCorr",
                        "string",
                        packageName
                    )
                )
                btnOptionTwo.setText(
                    this.resources.getIdentifier(
                        q_to_get + "F",
                        "string",
                        packageName
                    )
                )
                btnOptionOne.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("answer", "True")
                    startActivity(intent)
                    this.finish()
                }
            } catch (e: Exception) {
                btnOptionOne.setText(
                    this.resources.getIdentifier(
                        q_to_get + "T",
                        "string",
                        packageName
                    )
                )
                btnOptionTwo.setText(
                    this.resources.getIdentifier(
                        q_to_get + "FCorr",
                        "string",
                        packageName
                    )
                )

                btnOptionTwo.setOnClickListener {
                    val intent = Intent(this.applicationContext, SelectedCorrectActivity::class.java)
                    intent.putExtra("category", category)
                    intent.putExtra("question", question_text)
                    intent.putExtra("answer", "False")
                    startActivity(intent)
                    this.finish()
                }
            }
        }
    }
}