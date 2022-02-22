package com.example.triviaGame
import com.example.triviaGame.entities.PlayerEntity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.triviaGame.R
import com.example.triviaGame.LoginAnimation
import com.example.triviaGame.animations.MainScreenAnimation

import com.example.triviaGame.database.GlobalUser
import com.example.triviaGame.database.TriviaDatabase
import com.example.triviaGame.database.TriviaViewModel


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val dataSource = TriviaDatabase.getInstance(this)
            .TriviaDao
        val triviaViewModel = TriviaViewModel(dataSource, this
            .application)
        triviaViewModel.removeCurrentUser()
        val createAccount = findViewById<Button>(R.id.RegisterAccountButton)
        createAccount.setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameTextViewCreateAccount)
            val password = findViewById<EditText>(R.id.passwordTextViewCreateAccount)
            if (password.text.toString() != "" && username.text.toString() != "") {
                val account = PlayerEntity(
                    username.text.toString(),
                    password.text.toString(),
                    0,
                    0,
                    0,
                    true
                )
                GlobalUser.user = account
                if (!triviaViewModel.containsAccount(account)) {
                    triviaViewModel.setCurrentUser(account)
                    val intent = Intent(this, MainScreenAnimation::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "That username has already been used. Please choose another username.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show()
            }
        }

        val loginAccount: Button = findViewById(R.id.LoginAccountButton)
//        loginAccount.setOnClickListener {
//            val intent = Intent(this, LoginAnimation::class.java)
//            startActivity(intent)
//        }





        loginAccount.setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameTextViewCreateAccount)
            val password = findViewById<EditText>(R.id.passwordTextViewCreateAccount)
            if (password.text.toString() != "" && username.text.toString() != "") {
                val account = triviaViewModel.getAccount(username.text.toString(), password.text.toString())
                if (account != null) {
                    GlobalUser.user = account
                    if (triviaViewModel.containsAccount(account)) {
                        triviaViewModel.setCurrentUser(account)
                        val intent = Intent(this, LoginAnimation::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "That username has already been used. Please choose another username.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Login info not in database", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show()
            }
        }













    }
}